package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.WeeklyMission;
import com.example.stepperbackend.web.dto.WeeklyMissionDto;

public class WeeklyMissionConverter {

    public static WeeklyMission toEntity(WeeklyMissionDto.WeeklyMissionRequestDto dto) {
        return WeeklyMission.builder()
                .missionTitle(dto.getMissionTitle())
                .missionDescription(dto.getMissionDescription())
                .build();
    }

    public static WeeklyMissionDto.WeeklyMissionResponseDto toDto(WeeklyMission weeklyMission) {
        return WeeklyMissionDto.WeeklyMissionResponseDto.builder()
                .id(weeklyMission.getId())
                .missionTitle(weeklyMission.getMissionTitle())
                .missionDescription(weeklyMission.getMissionDescription())
                .build();
    }
}