package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.RateDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateDiaryRepository extends JpaRepository<RateDiary, Long> {

}
