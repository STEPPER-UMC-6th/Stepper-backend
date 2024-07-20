package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.service.MyExerciseService;
import com.example.stepperbackend.web.dto.MyExerciseRequestDTO;
import com.example.stepperbackend.web.dto.MyExerciseResponseDTO;
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
            return ApiResponse.onSuccess(MyExerciseResponseDTO.AddExerciseDTO.builder()
                            .message("나만의 운동 저장에 성공하였습니다.")
                            .status("success")
                    .build());
        }
    }

