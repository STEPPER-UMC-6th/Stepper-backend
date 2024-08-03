package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.service.WeeklyMissionService.WeeklyMissionService;
import com.example.stepperbackend.web.dto.WeeklyMissionDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/weekly-missions")
@RequiredArgsConstructor
public class WeeklyMissionController {

    private final WeeklyMissionService weeklyMissionService;


    @Operation(summary = "주간 미션 조회 API", description = "특정 주간 미션을 조회합니다.")
    @GetMapping("/{id}")
    public ApiResponse<WeeklyMissionDto.WeeklyMissionResponseDto> getWeeklyMission(@PathVariable Long id) {
        WeeklyMissionDto.WeeklyMissionResponseDto response = weeklyMissionService.getWeeklyMission(id);
        return ApiResponse.onSuccess(response);
    }

}
