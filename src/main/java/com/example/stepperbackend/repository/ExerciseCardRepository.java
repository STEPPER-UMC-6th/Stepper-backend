package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.enums.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface ExerciseCardRepository extends JpaRepository<ExerciseCard, Long> {
    Optional<ExerciseCard> findById(Long exerciseCardId);

    @Query("SELECT e FROM ExerciseCard e WHERE e.member = :member AND MONTH(e.date) = :month")
    List<ExerciseCard> findAllByMemberAndMonth(@Param("member") Member member, @Param("month") int month);

//    @Query("SELECT e FROM ExerciseCard e WHERE e.member = :member AND e.bodyPart = :bodyPart")
//    List<ExerciseCard> findByBodyPartAndEmail(@Param("bodyPart") BodyPart bodyPart, @Param("member") Member member);
//
//    List<ExerciseCard> findByBodyPartAndMember(BodyPart bodyPart, Member member);

    @Query("SELECT e FROM ExerciseCard e WHERE e.member = :member AND e.bodyPart = :bodyPart")
    List<ExerciseCard> findByBodyPartAndMember(@Param("bodyPart") BodyPart bodyPart, @Param("member") Member member);

}
