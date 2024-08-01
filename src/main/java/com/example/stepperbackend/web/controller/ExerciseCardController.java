package com.example.stepperbackend.web.controller;


import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.converter.ExerciseCardConverter;
import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.exerciseCardService.ExerciseCardService;
import com.example.stepperbackend.web.dto.ExerciseCardDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/exercise-card")
@RequiredArgsConstructor
public class ExerciseCardController {

    private final JWTUtil jwtUtil;
    final ExerciseCardService exerciseCardService;

    @Operation(summary = "운동 카드 추가 API", description = "운동 카드 추가")
    @PostMapping("/add")
    public ApiResponse<ExerciseCardDto.ExerciseCardResponseDto> addExerciseCard(@RequestBody ExerciseCardDto.ExerciseCardRequestDto dto, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        ExerciseCardDto.ExerciseCardResponseDto response = exerciseCardService.addExerciseCard(dto, email);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "운동 카드 상세 조회 API", description = "운동 카드 상세 조회")
    @PostMapping("/{exerciseId}/")
    public ApiResponse<ExerciseCardDto.ExerciseCardResponseDto> getExerciseCardDetail(@PathVariable(name = "exerciseId") Long exerciseId, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        ExerciseCardDto.ExerciseCardResponseDto response = exerciseCardService.getExerciseCardDetail(exerciseId);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "월별 운동 카드 상태 조회 API", description = "월별 운동 카드 상태 조회")
    @GetMapping
    public ApiResponse<List<ExerciseCardDto.ExerciseCardStatusResponseDto>> getExerciseStatusByMonth(@RequestParam("month") int month, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        List<ExerciseCardDto.ExerciseCardStatusResponseDto> response = exerciseCardService.getExerciseStatusByMonth(month, email);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "오늘의 운동 진행상태 조회 API", description = "오늘의 운동 진행상태 조회")
    @GetMapping("/today")
    public ApiResponse<List<ExerciseCardDto.ToDayExerciseResponseDto>> getToDayExercise(@RequestParam("date") LocalDate date) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ExerciseCardDto.ToDayExerciseResponseDto> response = exerciseCardService.getTodayExercises(date, memberId);
        return ApiResponse.onSuccess(response);
    }
}
