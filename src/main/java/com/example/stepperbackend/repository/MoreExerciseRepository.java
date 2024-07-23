package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.MoreExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoreExerciseRepository extends JpaRepository<MoreExercise, Long> {
}
