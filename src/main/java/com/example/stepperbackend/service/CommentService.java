package com.example.stepperbackend.service;

import com.example.stepperbackend.converter.CommentConverter;
import com.example.stepperbackend.domain.Comment;
import com.example.stepperbackend.repository.CommentRepository;
import com.example.stepperbackend.web.dto.CommentDto.CommentRequestDto;
import com.example.stepperbackend.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Comment comment = CommentConverter.toComment(requestDto);
        comment = commentRepository.save(comment);
        return CommentConverter.toCommentResponseDto(comment);
    }
}
