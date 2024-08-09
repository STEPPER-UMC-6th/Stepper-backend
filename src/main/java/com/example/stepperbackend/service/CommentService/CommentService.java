package com.example.stepperbackend.service.CommentService;

import com.example.stepperbackend.web.dto.CommentDto;
import com.example.stepperbackend.web.dto.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto.CommentResponseDto writeComment(CommentDto.CommentRequestDto request, String Email);

    List<CommentDto.CommentResponseDto> getComment(Long postId);

    CommentDto.CommentResponseDto writeReply(CommentDto.ReplyRequestDto request, String email);

}



