package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.ExerciseStepDto;

public class ExerciseStepConverter {

    public static ExerciseStep toEntity(ExerciseStepDto.ExerciseStepRequestDto dto, ExerciseCard exerciseCard, MyExercise myExercise) {
        return ExerciseStep.builder()
                .step(dto.getStep())
                .stepStatus(false)
                .exerciseCard(exerciseCard)
                .myExercise(myExercise)
                .build();
    }
}
