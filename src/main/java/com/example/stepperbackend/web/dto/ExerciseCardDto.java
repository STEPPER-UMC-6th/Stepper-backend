package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.domain.enums.Week;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ExerciseCardDto {

    @Getter
    public static class ExerciseCardRequestDto {
        private LocalDate date;
        private String week;
        private int hour;
        private int minute;
        private int second;
        private String materials;
        private String bodyPart;
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
        private int hour;
        private int minute;
        private int second;
        private String materials;
        private BodyPart bodyPart;

        private List<ExerciseStepDto.ExerciseStepResponseDto> stepList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseCardStatusResponseDto {
        private LocalDate date;
        private boolean status;
    }

    @Getter
    public static class ToDayExerciseRequestDto{
        private LocalDate date;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ToDayExerciseResponseDto {
        private Long id;
        private BodyPart bodyPart;

        private List<ExerciseStepDto.ExerciseStepResponseDto> stepList;
    }

    @Data
    public static class ExerciseCardWeekRequestDto {
        private String bodyPart;
    }

    @Data
    @Builder
    public static class ExerciseCardWeekResponseDto {
        private String bodyPart;
        private List<Week> weeks;
    }
}
