package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    public static class CommentRequestDto {
        private Long postId;
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDto {
        private Long postId;
        private Long commentId;
        private String profileImage;
        private String memberName;
        private String content;
        private LocalDateTime dateTime;
    }
}
