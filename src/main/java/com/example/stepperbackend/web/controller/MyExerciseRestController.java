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

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/MyExercise")
public class MyExerciseRestController {

    private final MyExerciseService myExerciseService;

        @PostMapping("/AddExercise")
        public ApiResponse<MyExerciseResponseDTO.AddExerciseDTO> Add(@RequestBody @Valid MyExerciseRequestDTO.AddExerciseDto request){
            MyExercise myExercise = myExerciseService.addMyExercise(request);
            return ApiResponse.onSuccess(MyExerciseConverter.toAddExerciseDTO(myExercise));
        }

        @PostMapping("/checkExercise")
        public ApiResponse<List<MyExerciseResponseDTO.CheckExerciseDTO>> check(@RequestBody @Valid MyExerciseRequestDTO.CheckExerciseDto request){
            List<MyExercise> myExercise = myExerciseService.checkMyExercise(request);
            System.out.println(ApiResponse.onSuccess(MyExerciseConverter.toCheckExerciseDTO(myExercise)));
            return ApiResponse.onSuccess(MyExerciseConverter.toCheckExerciseDTO(myExercise));
        }
    }
