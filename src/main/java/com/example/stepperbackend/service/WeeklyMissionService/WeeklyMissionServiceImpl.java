package com.example.stepperbackend.service.WeeklyMissionService;

import com.example.stepperbackend.converter.WeeklyMissionConverter;
import com.example.stepperbackend.domain.WeeklyMission;
import com.example.stepperbackend.repository.WeeklyMissionRepository;
import com.example.stepperbackend.web.dto.WeeklyMissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyMissionServiceImpl implements WeeklyMissionService {

    private final WeeklyMissionRepository weeklyMissionRepository;

    @Override
    public WeeklyMissionDto.WeeklyMissionResponseDto getWeeklyMission(Long id) {
        WeeklyMission weeklyMission = weeklyMissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Weekly Mission not found"));
        return WeeklyMissionConverter.toDto(weeklyMission);
    }
}