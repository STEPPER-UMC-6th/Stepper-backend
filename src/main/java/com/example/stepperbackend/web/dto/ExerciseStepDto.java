package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.web.dto.MyExercise.MyExerciseResponseDTO;
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
        private int step;
        private MyExerciseResponseDTO.CheckExerciseDTO myExercise;
    }
}
