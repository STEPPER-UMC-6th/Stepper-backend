package com.example.stepperbackend.service.exerciseStep;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.ExerciseStepHandler;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.repository.ExerciseStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExerciseStepServiceImpl implements ExerciseStepService{

    private final ExerciseStepRepository exerciseStepRepository;


    @Override
    public void modifyStep(Long stepId) {
        ExerciseStep exerciseStep = exerciseStepRepository.findById(stepId)
                .orElseThrow(() -> new ExerciseStepHandler(ErrorStatus.EXERCISE_STEP_ID_NOT_FOUND));

        exerciseStep.setStepStatus(true);
        exerciseStepRepository.save(exerciseStep);
    }

}
