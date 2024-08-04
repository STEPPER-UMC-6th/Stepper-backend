package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.WeeklyMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyMissionRepository extends JpaRepository<WeeklyMission, Long> {
}