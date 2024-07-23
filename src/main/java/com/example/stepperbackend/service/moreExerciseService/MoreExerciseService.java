package com.example.stepperbackend.service.moreExerciseService;


import com.example.stepperbackend.web.dto.MoreExerciseDto;

public interface MoreExerciseService {

    MoreExerciseDto.MoreExerciseResponseDto exerciseAdd(MoreExerciseDto.MoreExerciseRequestDto dto, String email);
}
