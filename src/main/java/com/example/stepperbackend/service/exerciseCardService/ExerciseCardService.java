package com.example.stepperbackend.service.exerciseCardService;

import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.web.dto.ExerciseCardDto;

import java.util.List;

public interface ExerciseCardService {

    ExerciseCardDto.ExerciseCardResponseDto addExerciseCard(ExerciseCardDto.ExerciseCardRequestDto dto, String email);

    ExerciseCardDto.ExerciseCardResponseDto getExerciseCardDetail(Long exerciseId);

    ExerciseCardDto.ExerciseCardResponseDto editExerciseCard(Long exerciseId, ExerciseCardDto.ExerciseCardRequestDto request);

    List<ExerciseCardDto.ExerciseCardStatusResponseDto> getExerciseStatusByMonth(int month, String email);

    List<ExerciseCardDto.ExerciseCardWeekResponseDto> getExerciseCardWeek(BodyPart bodyPart, String email);

}
