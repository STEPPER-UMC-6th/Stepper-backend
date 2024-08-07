package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExerciseStepDto {

    @Getter
    public static class ExerciseStepRequestDto {
        private Long myExerciseId;
        private int step;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseStepResponseDto {
        private Long stepId;
        private int step;
        private boolean step_status;
        private MyExerciseDto.CheckExerciseResponseDTO myExercise;
    }
}
