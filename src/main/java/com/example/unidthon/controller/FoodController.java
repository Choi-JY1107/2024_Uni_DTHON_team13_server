package com.example.unidthon.controller;

import java.net.URI;
import java.util.List;

import com.example.unidthon.entity.Money;
import com.example.unidthon.service.MoneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.unidthon.dto.FoodFormRequest;
import com.example.unidthon.dto.FoodRecommendResponse;
import com.example.unidthon.dto.FoodResponse;
import com.example.unidthon.dto.OcrRecord;
import com.example.unidthon.entity.Food;
import com.example.unidthon.service.FoodService;
import com.example.unidthon.service.OcrService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;
    private final OcrService ocrService;

    public FoodController(FoodService foodService, OcrService ocrService) {
        this.foodService = foodService;
        this.ocrService = ocrService;
    }

    @Operation(summary = "모든 음식 조회", description = "냉장고에 있는 모든 음식을 조회합니다.")
    @GetMapping
    public List<FoodResponse> getAllFoods() {
        return foodService.getAllFoods();
    }

    @Operation(summary = "특정 ID의 음식 조회", description = "특정 ID에 해당하는 음식을 조회합니다.")
    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @Operation(summary = "음식 저장", description = "새로운 음식을 냉장고에 저장합니다.")
    @PostMapping
    public ResponseEntity<Long> saveFood(@RequestBody FoodFormRequest foodFormRequest) {
        Food food = new Food(
                null,
                foodFormRequest.getName(),
                foodFormRequest.getExpiryDate(),
                foodFormRequest.getPurchaseDate(),
                foodFormRequest.getPrice()
        );
        Long savedFoodId = foodService.saveFood(food);

        // Location 헤더 설정
        URI location = URI.create("/food/" + savedFoodId);
        return ResponseEntity.created(location).body(savedFoodId);
    }

    @Operation(summary = "OCR을 통한 음식 검색", description = "OCR 기능을 통해 영수증을 분석하고 음식 정보를 반환합니다.")
    @PostMapping("/searching/ocr")
    public ResponseEntity<List<OcrRecord>> searchFoodByOCR(@RequestParam("file") MultipartFile file) {
        // OCR 분석
        List<OcrRecord> ocrRecords = ocrService.analyzeReceipt(file);

        // OCR 결과를 FoodService로 전달하여 저장
        foodService.saveFoodByOcrRecords(ocrRecords);

        // OCR 결과 반환
        return ResponseEntity.ok(ocrRecords);
    }

    @Operation(summary = "오늘 저녁 추천", description = "냉장고에 있는 재료를 이용해 GPT가 추천한 레시피 목록을 반환합니다.")
    @GetMapping("/recommend")
    public List<FoodRecommendResponse> getFoodRecommendations() {
        return foodService.getFoodRecommendations();
    }


}
