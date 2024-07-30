package com.example.stepperbackend.repository;

import com.example.stepperbackend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
