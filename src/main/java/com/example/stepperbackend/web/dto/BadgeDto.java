package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BadgeDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BadgeResponseDto{
        private Long id;
        private String badgeName;
        private String explanation;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BadgeListDto{
        private Long id;
        private String categoryName;
        private List<BadgeResponseDto> list;
    }
}
