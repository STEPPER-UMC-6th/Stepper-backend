package com.example.stepperbackend.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
        private LocalDate date;
        private int hour;
        private int minutes;
        private int seconds;
    }

}
