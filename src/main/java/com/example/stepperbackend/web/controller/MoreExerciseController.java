package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.service.moreExerciseService.MoreExerciseService;
import com.example.stepperbackend.web.dto.MoreExerciseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class MoreExerciseController {

    final MoreExerciseService moreExerciseService;

    @Operation(summary = "추가 운동 기록 API",description = "추가 운동 기록")
    @PostMapping("/more")
    public ApiResponse<MoreExerciseDto.MoreExerciseResponseDto> exerciseAdd(@RequestBody MoreExerciseDto.MoreExerciseRequestDto dto, @RequestParam String email) {

        MoreExerciseDto.MoreExerciseResponseDto response = moreExerciseService.exerciseAdd(dto,email);
        return ApiResponse.onSuccess(response);
    }
}
