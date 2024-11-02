package com.example.unidthon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.unidthon.dto.OcrRecord;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service
public class OcrService {

    public List<OcrRecord> analyzeReceipt(MultipartFile file) {
        try {
            // MultipartFile을 ByteString으로 변환
            ByteString imgBytes = ByteString.copyFrom(file.getBytes());

            // Google Vision API 요청 생성
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            // Google Vision API 클라이언트로 요청 전송 및 응답 받기
            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
                AnnotateImageResponse response = client.batchAnnotateImages(requests).getResponsesList().get(0);

                if (response.hasError()) {
                    System.err.println("Error: " + response.getError().getMessage());
                    return null;
                }

                // OCR 결과 텍스트 추출 및 제품 목록 파싱
                String extractedText = extractTextFromResponse(response);
                return parseTextToOcrRecords(extractedText);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to analyze receipt with Google Vision OCR", e);
        }
    }

    private String extractTextFromResponse(AnnotateImageResponse response) {
        StringBuilder extractedText = new StringBuilder();
        for (EntityAnnotation annotation : response.getTextAnnotationsList()) {
            extractedText.append(annotation.getDescription()).append("\n");
        }
        return extractedText.toString();
    }

    private List<OcrRecord> parseTextToOcrRecords(String text) {
        List<OcrRecord> products = new ArrayList<>();
        Pattern pattern = Pattern.compile("([가-힣a-zA-Z)]+[가-힣a-zA-Z0-9]+)|([0-9,]+)");
        Matcher matcher = pattern.matcher(text);

        String currentName = null;
        Integer unitPrice = null;
        System.out.println(text);

        while (matcher.find()) {
            String match = matcher.group();

            if (currentName == null) {
                // 상품명 추출 단계
                if (match.matches("[가-힣a-zA-Z)]+[가-힣a-zA-Z0-9]+")) {
                    currentName = match;
                }
            } else if (unitPrice == null) {
                // 단가 추출 단계
                if (match.matches("[0-9,]+")) {
                    int parsedPrice = Integer.parseInt(match.replace(",", ""));
                    if (parsedPrice >= 100) {
                        unitPrice = parsedPrice;
                    } else {
                        // 단가가 100 미만인 경우 초기화 후 상품명 단계로
                        currentName = null;
                    }
                } else {
                    // 단가가 숫자가 아닌 경우 초기화 후 상품명 단계로
                    currentName = null;
                }
            } else {
                // 수량 추출 단계
                if (match.matches("[0-9]+")) {
                    int quantity = Integer.parseInt(match);
                    if (quantity <= 100) {
                        // 모든 조건을 만족하면 OcrRecord에 추가
                        products.add(new OcrRecord(currentName, null, null, unitPrice * quantity));
                    }
                    // 상품명 단계로 초기화
                    currentName = null;
                    unitPrice = null;
                } else {
                    // 수량이 숫자가 아닌 경우 초기화 후 상품명 단계로
                    currentName = null;
                    unitPrice = null;
                }
            }
        }

        return products;
    }
}
