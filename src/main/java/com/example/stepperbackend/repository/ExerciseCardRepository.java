package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseCardRepository extends JpaRepository<ExerciseCard, Long> {
}
