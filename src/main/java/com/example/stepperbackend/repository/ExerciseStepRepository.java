package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseStepRepository extends JpaRepository<ExerciseStep, Long> {
}
