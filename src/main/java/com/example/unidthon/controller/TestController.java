package com.example.unidthon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Tag(name = "test", description = "test api 입니다.")
public class TestController {

    @GetMapping("")
    @Operation(summary = "테스트", description = "테스트입니다.")
    public String test(){
        return "test";
    }
}
