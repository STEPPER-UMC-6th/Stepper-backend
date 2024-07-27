package com.example.stepperbackend.service.moreExerciseService;


import com.example.stepperbackend.web.dto.MoreExerciseDto;

import java.time.LocalDate;
import java.util.List;

public interface MoreExerciseService {

    MoreExerciseDto.MoreExerciseResponseDto exerciseAdd(MoreExerciseDto.MoreExerciseRequestDto dto, String email);

    List<MoreExerciseDto.MoreExerciseResponseDto> getMoreExerciseList(String email, LocalDate date);
}
