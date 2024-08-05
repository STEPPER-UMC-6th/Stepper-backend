package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT count(e) FROM Post e WHERE e.member = :member")
    int getCountByMember(@Param("member") Member member);
}
