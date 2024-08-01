package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Comment;
import com.example.stepperbackend.web.dto.CommentDto.CommentRequestDto;
import com.example.stepperbackend.web.dto.CommentDto.CommentResponseDto;

import java.time.format.DateTimeFormatter;

public class CommentConverter {

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .postId(comment.getPostId())
                .authorId(comment.getAuthorId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static Comment toComment(CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .postId(commentRequestDto.getPostId())
                .authorId(commentRequestDto.getAuthorId())
                .content(commentRequestDto.getContent())
                .build();
    }
}
