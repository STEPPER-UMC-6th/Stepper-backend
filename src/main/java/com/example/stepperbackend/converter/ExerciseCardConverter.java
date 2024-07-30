package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.domain.enums.Week;
import com.example.stepperbackend.web.dto.ExerciseCardDto;
import com.example.stepperbackend.web.dto.ExerciseStepDto;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseCardConverter {

    public static ExerciseCard toEntity(ExerciseCardDto.ExerciseCardRequestDto dto, Member member) {
        return ExerciseCard.builder()
                .date(dto.getDate())
                .week(Week.valueOf(dto.getWeek().toString()))
                .hour(dto.getHour())
                .minute(dto.getMinute())
                .second(dto.getSecond())
                .status(false)
                .materials(dto.getMaterials())
                .bodyPart(BodyPart.valueOf(dto.getBodyPart().toString()))
                .member(member)
                .build();
    }

    public static ExerciseCardDto.ExerciseCardResponseDto toDto(ExerciseCard exerciseCard, List<ExerciseStep> exerciseSteps) {

        List<ExerciseStepDto.ExerciseStepResponseDto> exerciseStepList = exerciseSteps.stream()
                .map(ExerciseStepConverter::toDto).collect(Collectors.toList());

        return ExerciseCardDto.ExerciseCardResponseDto.builder()
                .id(exerciseCard.getId())
                .date(exerciseCard.getDate())
                .week(exerciseCard.getWeek())
                .hour(exerciseCard.getHour())
                .minute(exerciseCard.getMinute())
                .second(exerciseCard.getSecond())
                .materials(exerciseCard.getMaterials())
                .bodyPart(exerciseCard.getBodyPart())
                .setpList(exerciseStepList)
                .build();
    }
}
