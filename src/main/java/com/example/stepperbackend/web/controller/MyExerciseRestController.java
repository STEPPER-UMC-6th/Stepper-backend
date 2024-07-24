package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.service.MyExerciseService;
import com.example.stepperbackend.web.converter.MyExerciseConverter;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseRequestDTO;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/MyExercise")
public class MyExerciseRestController {

    private final MyExerciseService myExerciseService;

        @PostMapping("/AddExercise")
        public ApiResponse<MyExerciseResponseDTO.AddExerciseDTO> join(@RequestBody @Valid MyExerciseRequestDTO.AddExerciseDto request){
            MyExercise myExercise = myExerciseService.addMyExercise(request);
            return ApiResponse.onSuccess(MyExerciseConverter.toAddExerciseDTO(myExercise));
        }

        @PostMapping("/CheckExercise")
        public ApiResponse<MyExerciseResponseDTO.CheckExerciseDTO> check(@RequestBody @Valid MyExerciseRequestDTO.CheckExerciseDto request){
            MyExercise myExercise = myExerciseService.checkMyExercise(request);
            return ApiResponse.onSuccess(MyExerciseConverter.tocheckExerciseDTO(myExercise));
        }
    }

