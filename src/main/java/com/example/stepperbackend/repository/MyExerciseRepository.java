package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.MyExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyExerciseRepository extends JpaRepository<MyExercise, Long> {

}
