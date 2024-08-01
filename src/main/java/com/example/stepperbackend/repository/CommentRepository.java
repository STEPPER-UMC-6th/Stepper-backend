package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
