package com.example.stepperbackend.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class CommentRequestDto {
        private Long postId;
        private Long authorId;
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDto {
        private Long commentId;
        private Long postId;
        private Long authorId;
        private String content;
        private LocalDateTime createdAt;
    }
}