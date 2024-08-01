package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.ExerciseStepDto;
import com.example.stepperbackend.web.dto.MyExerciseDto;

public class ExerciseStepConverter {

    public static ExerciseStep toEntity(ExerciseStepDto.ExerciseStepRequestDto dto, ExerciseCard exerciseCard, MyExercise myExercise) {
        return ExerciseStep.builder()
                .step(dto.getStep())
                .stepStatus(false)
                .exerciseCard(exerciseCard)
                .myExercise(myExercise)
                .build();
    }

    public static ExerciseStepDto.ExerciseStepResponseDto toDto(ExerciseStep step) {

        MyExerciseDto.CheckExerciseResponseDTO myExerciseDto = MyExerciseConverter.toCheckExerciseDTO(step.getMyExercise());

        return ExerciseStepDto.ExerciseStepResponseDto.builder()
                .step(step.getStep())
                .myExercise(myExerciseDto)
                .build();
    }
}
