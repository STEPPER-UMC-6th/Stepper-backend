package com.example.stepperbackend.web.dto;

import lombok.Getter;

public class ExerciseStepDto {

    @Getter
    public static class ExerciseStepRequestDto {
        private Long myExerciseId;
        private int step;
    }
}
