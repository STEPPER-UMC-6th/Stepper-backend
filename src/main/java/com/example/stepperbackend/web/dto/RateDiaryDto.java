package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class RateDiaryDto {

    @Getter
    public static class RateDiaryRequestDTO {
        private Long conditionRate;
        private Long painRate;
        private String painMemo;
        private String painImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RateDiaryResponseDTO {
        private Long id;
        private String status;
        private String message;
    }
}
