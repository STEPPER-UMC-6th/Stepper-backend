package com.example.stepperbackend.web.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MoreExercise;
import com.example.stepperbackend.web.dto.MoreExerciseDto;

public class MoreExerciseConverter {

    public static MoreExercise toEntity(MoreExerciseDto.MoreExerciseRequestDto dto, Member member) {
        return MoreExercise.builder()
                .hour(dto.getHour())
                .minutes(dto.getMinutes())
                .seconds(dto.getSeconds())
                .member(member)
                .build();
    }

    public static MoreExerciseDto.MoreExerciseResponseDto toDto(MoreExercise moreExercise) {
        return MoreExerciseDto.MoreExerciseResponseDto.builder()
                .id(moreExercise.getId())
                .hour(moreExercise.getHour())
                .minutes(moreExercise.getMinutes())
                .seconds(moreExercise.getSeconds())
                .build();
    }

}
