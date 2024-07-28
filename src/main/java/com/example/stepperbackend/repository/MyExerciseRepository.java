package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.MyExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyExerciseRepository extends JpaRepository<MyExercise, Long> {

}

