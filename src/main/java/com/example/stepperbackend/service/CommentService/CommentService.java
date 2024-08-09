package com.example.stepperbackend.service.CommentService;

import com.example.stepperbackend.web.dto.CommentDto;

public interface CommentService {
    CommentDto.CommentResponseDto writeComment(CommentDto.CommentRequestDto request, String Email);

    public CommentDto.CommentResponseDto writeReply(CommentDto.ReplyRequestDto request, String email);

    }
