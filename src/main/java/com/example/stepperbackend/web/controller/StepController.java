package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.service.exerciseStep.ExerciseStepService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exerciseStep")
public class StepController {

    private final ExerciseStepService exerciseStepService;

    @Operation(summary = "운동카드 단계별 상태 수정 API", description = "단계별 상태 수정")
    @PostMapping("/step")
    public ApiResponse<Void> modify(@RequestParam Long stepId) {

        exerciseStepService.modifyStep(stepId);
        return ApiResponse.onSuccess(null);
    }
}
