package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.service.MyExerciseService.MyExerciseService;
import com.example.stepperbackend.converter.MyExerciseConverter;
import com.example.stepperbackend.web.dto.MyExerciseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myexercise")
public class MyExerciseRestController {

    private final MyExerciseService myExerciseService;


        @PostMapping("/add")
        @Operation(summary = "나만의 운동 추가 API",description = "나만의 운동 추가")
        public ApiResponse<MyExerciseDto.AddExerciseResponseDTO> Add(@RequestBody @Valid MyExerciseDto.AddExerciseRequestDto request){
            String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

            MyExerciseDto.AddExerciseResponseDTO myExercise = myExerciseService.addMyExercise(request, memberId);
            return ApiResponse.onSuccess(myExercise);
        }

        @PostMapping("/check")
        @Operation(summary = "나만의 운동 조회 API",description = "나만의 운동 조회")
        public ApiResponse<List<MyExerciseDto.CheckExerciseResponseDTO>> check(@RequestBody @Valid MyExerciseDto.CheckExerciseRequestDto request){
            String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

            List<MyExercise> myExercise = myExerciseService.checkMyExercise(request, memberId);
            return ApiResponse.onSuccess(MyExerciseConverter.toCheckExerciseDTO(myExercise));
        }

    }

