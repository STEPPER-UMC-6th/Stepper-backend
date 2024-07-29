package com.example.stepperbackend.service.exerciseCardService;

import com.example.stepperbackend.web.dto.ExerciseCardDto;

public interface ExerciseCardService {

    ExerciseCardDto.ExerciseCardResponseDto addExerciseCard(ExerciseCardDto.ExerciseCardRequestDto dto, String email);

}
