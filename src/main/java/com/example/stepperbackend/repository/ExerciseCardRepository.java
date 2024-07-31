package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseCardRepository extends JpaRepository<ExerciseCard, Long> {
    Optional<ExerciseCard> findById(Long exerciseCardId);
}
