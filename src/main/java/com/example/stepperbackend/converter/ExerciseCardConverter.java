package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.domain.enums.Week;
import com.example.stepperbackend.web.dto.ExerciseCardDto;
import com.example.stepperbackend.web.dto.ExerciseStepDto;

import java.util.ArrayList;
import java.util.Collections;
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
                .stepList(exerciseStepList)
                .build();
    }

    public static ExerciseCard updateEntity(ExerciseCard existingExerciseCard, ExerciseCardDto.ExerciseCardRequestDto dto) {
        existingExerciseCard.setDate(dto.getDate());
        existingExerciseCard.setWeek(Week.valueOf(dto.getWeek().toString()));
        existingExerciseCard.setHour(dto.getHour());
        existingExerciseCard.setMinute(dto.getMinute());
        existingExerciseCard.setSecond(dto.getSecond());
        existingExerciseCard.setMaterials(dto.getMaterials());
        existingExerciseCard.setBodyPart(BodyPart.valueOf(dto.getBodyPart()));
        return existingExerciseCard;
    }

    public static ExerciseCardDto.ExerciseCardStatusResponseDto toStatusResponseDto(ExerciseCard exerciseCard) {
        return ExerciseCardDto.ExerciseCardStatusResponseDto.builder()
                .date(exerciseCard.getDate())
                .status(exerciseCard.isStatus())
                .bodyPart(exerciseCard.getBodyPart())
                .build();
    }


    public static List<ExerciseCardDto.ToDayExerciseResponseDto> toDayExerciseListDto(List<ExerciseCard> exerciseCardList, List<ExerciseStep> exerciseStepList) {

        // 결과 DTO 리스트 초기화
        List<ExerciseCardDto.ToDayExerciseResponseDto> responseDtoList = new ArrayList<>();

        // 각 운동 카드에 대해 DTO 생성
        for (ExerciseCard exerciseCard : exerciseCardList) {
            // 해당 카드에 대한 운동 단계 필터링
            List<ExerciseStep> stepsForCard = exerciseStepList.stream()
                    .filter(step -> step.getExerciseCard().equals(exerciseCard))
                    .toList();

            // DTO 생성
            ExerciseCardDto.ToDayExerciseResponseDto responseDto = ExerciseCardDto.ToDayExerciseResponseDto.builder()
                    .id(exerciseCard.getId())
                    .bodyPart(exerciseCard.getBodyPart())
                    .stepList(stepsForCard.stream()
                            .map(ExerciseStepConverter::toDto)
                            .collect(Collectors.toList()))
                    .build();

            // 결과 리스트에 추가
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }
}



