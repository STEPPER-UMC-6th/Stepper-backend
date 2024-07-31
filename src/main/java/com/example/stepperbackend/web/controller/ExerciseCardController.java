package com.example.stepperbackend.web.controller;


import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.exerciseCardService.ExerciseCardService;
import com.example.stepperbackend.web.dto.ExerciseCardDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "운동 카드 수정 API", description = "운동 카드 수정")
    @PutMapping("/{exerciseId}/edit")
    public ApiResponse<ExerciseCardDto.ExerciseCardResponseDto> editExerciseCard(@PathVariable(name="exerciseId") Long exerciseId, @RequestBody ExerciseCardDto.ExerciseCardRequestDto dto, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        ExerciseCardDto.ExerciseCardResponseDto response = exerciseCardService.editExerciseCard(exerciseId, dto);
        return ApiResponse.onSuccess(response);
    }
}
