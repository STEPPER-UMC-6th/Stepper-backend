package com.example.stepperbackend.web.dto;

import jakarta.persistence.Access;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserCommentsDto {
    @Data
    @AllArgsConstructor
    public static class CommentDetail{
        private Long commentId;
        private Long postId;
        private Long authorId;
        private String content;
        private LocalDateTime createdAt;
    }
    private List<CommentDetail> comments;

}
