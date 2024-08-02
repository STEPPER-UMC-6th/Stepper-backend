package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.SubCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class PostDto {

    @Data
    public static class PostRequestDto {
        private String imageUrl;
        private String title;
        private String body;
        private Long categoryId;
        private SubCategory subCategory;
    }

    @Data
    @Builder
    public static class PostResponseDto {
        private Long id;
        private String imageUrl;
        private String title;
        private String body;
        private String authorEmail;
        private Long categoryId;
        private SubCategory subCategory;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}