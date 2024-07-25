package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class PostRequestDto {
        private String title;
        private String content;
        private Long authorId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostResponseDto {
        private Long postId;
        private String title;
        private LocalDateTime createdAt;
    }
}
