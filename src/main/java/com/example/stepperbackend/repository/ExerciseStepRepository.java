package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseStepRepository extends JpaRepository<ExerciseStep, Long> {

    List<ExerciseStep> findAllByExerciseCard(ExerciseCard exerciseCard);
}
