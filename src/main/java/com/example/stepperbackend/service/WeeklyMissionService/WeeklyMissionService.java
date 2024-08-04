package com.example.stepperbackend.service.WeeklyMissionService;

import com.example.stepperbackend.web.dto.WeeklyMissionDto;

import java.util.List;

public interface WeeklyMissionService {
    WeeklyMissionDto.WeeklyMissionResponseDto getWeeklyMission(Long id);
    //List<WeeklyMissionDto.WeeklyMissionResponseDto> getAllWeeklyMissions();
}
