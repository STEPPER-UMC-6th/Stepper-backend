package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.enums.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMember(Member member);

    @Query("SELECT count(e) FROM Post e WHERE e.member = :member")
    int getCountByMember(@Param("member") Member member);

    @Query("SELECT e FROM Post e WHERE e.bodyPart = :bodypart")
    List<Post> findByCategoryId(@Param("bodypart") BodyPart bodyPart);


}
