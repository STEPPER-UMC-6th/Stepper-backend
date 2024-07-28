package com.example.stepperbackend.service.MyExerciseService;

import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseRequestDTO;

import java.util.List;

public interface MyExerciseService {

    public MyExercise addMyExercise(MyExerciseRequestDTO.AddExerciseDto request, String memberId);

    public List<MyExercise> checkMyExercise(MyExerciseRequestDTO.CheckExerciseDto request, String memberId);
}
