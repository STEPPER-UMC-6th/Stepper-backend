package com.example.stepperbackend.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



public class RateDiaryDto {

    @Getter
    public static class RateDiaryWriteRequestDTO {
        private Long exerciseCardId;
        private Long conditionRate;
        private Long painRate;
        private String painMemo;
        private String painImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RateDiaryWriteResponseDTO {
        private Long rate_id;
    }



    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RateDiaryCheckResponseDTO {
        private LocalDate date;
        private Long exerciseCardId;
        private String bodyPart;
        private Long conditionRate;
        private Long painRate;
        private String painMemo;
        private String painImage;
    }
}
