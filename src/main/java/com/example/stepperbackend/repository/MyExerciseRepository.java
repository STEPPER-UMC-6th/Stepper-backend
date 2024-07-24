package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.MyExercise;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MyExerciseRepository extends JpaRepository<MyExercise, Long> {

    Page<MyExercise> findByMember();
}

