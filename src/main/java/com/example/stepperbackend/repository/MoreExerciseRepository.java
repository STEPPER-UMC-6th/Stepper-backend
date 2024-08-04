package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MoreExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoreExerciseRepository extends JpaRepository<MoreExercise, Long> {

    List<MoreExercise> findAllByMemberAndDate(Member member, LocalDate date);

    @Query("SELECT count(e) FROM MoreExercise e WHERE e.member = :member")
    int getCountByMember(Member member);
}
