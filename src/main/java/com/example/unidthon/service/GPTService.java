package com.example.unidthon.service;

import org.springframework.stereotype.Service;

@Service
public class GPTService {

    public String getRecipeRecommendations() {
        // Mock 데이터 반환
        return """
                [
                    {
                        "재료": ["김치", "쌀", "계란"],
                        "제목": "김치볶음밥",
                        "요리법": "1. 팬에 기름을 두르고 김치를 볶는다. 2. 쌀밥을 넣고 함께 볶아준다. 3. 마지막에 계란을 위에 얹어 익힌다.",
                        "시간": "10분",
                        "난이도": "쉬움",
                        "사진URL": "https://example.com/kimchi_fried_rice.jpg"
                    },
                    {
                        "재료": ["파스타면", "베이컨", "계란"],
                        "제목": "까르보나라",
                        "요리법": "1. 스파게티 면을 소금물에 삶는다. 2. 베이컨을 올리브유에 볶아준다. 3. 삶은 면과 베이컨을 섞고 계란 노른자, 치즈, 후추를 넣어 버무린다.",
                        "시간": "20분",
                        "난이도": "쉬움",
                        "사진URL": "https://example.com/carbonara.jpg"
                    },
                    {
                        "재료": ["냉동 우동면", "굴소스", "계란"],
                        "제목": "굴소스 우동",
                        "요리법": "1. 냉동 우동면을 끓는 물에 데친 후 찬물에 헹군다. 2. 팬에 기름을 두르고 계란을 스크램블 형태로 익힌다. 3. 굴소스를 넣고 면과 함께 볶아준다.",
                        "시간": "15분",
                        "난이도": "쉬움",
                        "사진URL": "https://example.com/oyster_sauce_udon.jpg"
                    }
                ]
                """;
    }
}
