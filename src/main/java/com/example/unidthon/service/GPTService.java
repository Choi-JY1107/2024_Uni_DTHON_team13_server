package com.example.unidthon.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GPTService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GPT_API_URL = "https://v0q7gdqzpb.execute-api.us-east-1.amazonaws.com/recommend";

    // userPrompt를 JSON 문자열로 받아 API에 전송
    public String getRecipeRecommendations(String userPromptJson) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // JSON 문자열을 HttpEntity로 설정
            HttpEntity<String> requestEntity = new HttpEntity<>(userPromptJson, headers);

            // API에 POST 요청 보내기
            ResponseEntity<String> response = restTemplate.exchange(
                    GPT_API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching recommendations";
        }
    }
}
