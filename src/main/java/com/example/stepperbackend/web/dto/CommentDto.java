package com.example.stepperbackend.web.dto;

import jakarta.validation.constraints.NotBlank;
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
        private boolean anonymous;
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

    @Getter
    public static class ReplyRequestDto {
        private Long postId;
        private Long parentCommentId;
        private String content;
        private boolean anonymous;
    }

}
