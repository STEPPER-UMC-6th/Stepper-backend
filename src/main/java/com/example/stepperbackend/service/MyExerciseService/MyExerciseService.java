package com.example.stepperbackend.service.MyExerciseService;

import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.MyExerciseDto;

import java.util.List;

public interface MyExerciseService {

    public MyExercise addMyExercise(MyExerciseDto.AddExerciseRequestDto request, String memberId);

    public List<MyExercise> checkMyExercise(MyExerciseDto.CheckExerciseRequestDto request, String memberId);
}
