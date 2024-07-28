package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.moreExerciseService.MoreExerciseService;
import com.example.stepperbackend.web.dto.MoreExerciseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class MoreExerciseController {

    final MoreExerciseService moreExerciseService;
    private final JWTUtil jwtUtil;


    @Operation(summary = "추가 운동 기록 API",description = "추가 운동 기록")
    @PostMapping("/more")
    public ApiResponse<MoreExerciseDto.MoreExerciseResponseDto> exerciseAdd(@RequestBody MoreExerciseDto.MoreExerciseRequestDto dto, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        MoreExerciseDto.MoreExerciseResponseDto response = moreExerciseService.exerciseAdd(dto,email);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "추가 운동 기록 조회 API",description = "추가 운동 기록 조회")
    @GetMapping("/more")
    public ApiResponse<List<MoreExerciseDto.MoreExerciseResponseDto>> getMoreExercise(@RequestParam LocalDate date, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        List<MoreExerciseDto.MoreExerciseResponseDto> response = moreExerciseService.getMoreExerciseList(email, date);
        return ApiResponse.onSuccess(response);
    }
}
