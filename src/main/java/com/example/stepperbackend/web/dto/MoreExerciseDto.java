package com.example.stepperbackend.web.dto;

import lombok.*;

public class MoreExerciseDto {

    @Getter
    public static class MoreExerciseRequestDto{
        private int hour;
        private int minutes;
        private int seconds;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MoreExerciseResponseDto{
        private Long id;
        private int hour;
        private int minutes;
        private int seconds;
    }
}
