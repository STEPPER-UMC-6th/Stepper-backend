package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_IdAndMember_Id(Long post_id, Long member_id);
}
