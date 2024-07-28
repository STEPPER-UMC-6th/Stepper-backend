package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.BadgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeCategoryRepository extends JpaRepository<BadgeCategory, Long> {
}
