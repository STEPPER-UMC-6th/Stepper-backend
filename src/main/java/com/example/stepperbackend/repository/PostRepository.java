package com.stepper.stepperbackend.repository;

import com.stepper.stepperbackend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
