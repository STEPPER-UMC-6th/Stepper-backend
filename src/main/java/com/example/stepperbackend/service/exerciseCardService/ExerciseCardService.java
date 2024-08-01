package com.example.stepperbackend.service.exerciseCardService;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.web.dto.ExerciseCardDto;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseCardService {

    ExerciseCardDto.ExerciseCardResponseDto addExerciseCard(ExerciseCardDto.ExerciseCardRequestDto dto, String email);

    ExerciseCardDto.ExerciseCardResponseDto getExerciseCardDetail(Long exerciseId);

    ExerciseCardDto.ExerciseCardResponseDto editExerciseCard(Long exerciseId, ExerciseCardDto.ExerciseCardRequestDto request);

    List<ExerciseCardDto.ExerciseCardStatusResponseDto> getExerciseStatusByMonth(int month, String email);

    List<ExerciseCardDto.ToDayExerciseResponseDto> getTodayExercises(LocalDate date, String memberEmail);
}
