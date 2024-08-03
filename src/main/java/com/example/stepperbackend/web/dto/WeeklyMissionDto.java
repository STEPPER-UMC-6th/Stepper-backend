package com.example.stepperbackend.web.dto;


import lombok.Builder;
import lombok.Data;

public class WeeklyMissionDto {

    @Data
    public static class WeeklyMissionRequestDto {
        private String missionTitle;
        private String missionDescription;

    }

    @Data
    @Builder
    public static class WeeklyMissionResponseDto {
        private Long id;
        private String missionTitle;
        private String missionDescription;

    }
}