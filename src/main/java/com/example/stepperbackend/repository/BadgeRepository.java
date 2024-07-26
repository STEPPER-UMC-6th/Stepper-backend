package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.BadgeCategory;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.mapping.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findAllByBadgeCategoryAndMember(BadgeCategory badgeCategory, Member member);
}
