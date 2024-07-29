package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.enums.Week;
import com.example.stepperbackend.web.dto.ExerciseCardDto;

public class ExerciseCardConverter {

    public static ExerciseCard toEntity(ExerciseCardDto.ExerciseCardRequestDto dto, Member member) {
        return ExerciseCard.builder()
                .date(dto.getDate())
                .week(Week.valueOf(dto.getWeek()))
                .stopwatch(dto.getStopWatch())
                .status(false)
                .materials(dto.getMaterials())
                .bodyPart(dto.getBodyPart())
                .member(member)
                .build();
    }

    public static ExerciseCardDto.ExerciseCardResponseDto toDto(ExerciseCard exerciseCard) {
        return ExerciseCardDto.ExerciseCardResponseDto.builder()
                .id(exerciseCard.getId())
                .date(exerciseCard.getDate())
                .week(exerciseCard.getWeek())
                .stopWatch(exerciseCard.getStopwatch())
                .materials(exerciseCard.getMaterials())
                .bodyPart(exerciseCard.getBodyPart())
                .build();
    }
}
