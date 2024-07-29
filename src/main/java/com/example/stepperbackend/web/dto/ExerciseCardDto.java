package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.domain.enums.Week;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ExerciseCardDto {

    @Getter
    public static class ExerciseCardRequestDto {
        private LocalDate date;
        private String week;
        private LocalTime stopWatch;
        private String materials;
        private BodyPart bodyPart;
        private List<ExerciseStepDto.ExerciseStepRequestDto> stepList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseCardResponseDto {
        private Long id;
        private LocalDate date;
        private Week week;
        private LocalTime stopWatch;
        private String materials;
        private BodyPart bodyPart;
    }
}
