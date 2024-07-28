package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.MyExerciseService.MyExerciseService;
import com.example.stepperbackend.service.MyExerciseService.MyExerciseServiceImpl;
import com.example.stepperbackend.converter.MyExerciseConverter;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseRequestDTO;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myexercise")
public class MyExerciseRestController {

    private final MyExerciseServiceImpl myExerciseService;

    @PostMapping("/add")
    @Operation(summary = "나만의 운동 추가 API", description = "나만의 운동 추가")
    public ApiResponse<MyExerciseResponseDTO.AddExerciseDTO> Add(@RequestBody @Valid MyExerciseRequestDTO.AddExerciseDto request) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MyExercise myExercise = myExerciseService.addMyExercise(request, memberId);
        return ApiResponse.onSuccess(MyExerciseConverter.toAddExerciseDTO(myExercise));
    }

    @PostMapping("/check")
    @Operation(summary = "나만의 운동 조회 API", description = "나만의 운동 조회")
    public ApiResponse<List<MyExerciseResponseDTO.CheckExerciseDTO>> check(@RequestBody @Valid MyExerciseRequestDTO.CheckExerciseDto request) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<MyExercise> myExercise = myExerciseService.checkMyExercise(request, memberId);
        return ApiResponse.onSuccess(MyExerciseConverter.toCheckExerciseDTO(myExercise));
    }

}

