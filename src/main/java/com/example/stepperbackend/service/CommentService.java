package com.example.stepperbackend.service;

import com.example.stepperbackend.converter.CommentConverter;
import com.example.stepperbackend.domain.Comment;
import com.example.stepperbackend.repository.CommentRepository;
import com.example.stepperbackend.web.dto.CommentDto.CommentRequestDto;
import com.example.stepperbackend.web.dto.CommentDto.CommentResponseDto;
import com.example.stepperbackend.web.dto.UserCommentsDto;
import com.example.stepperbackend.web.dto.UserCommentsDto.CommentDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public UserCommentsDto getUserComments(Long userId) {
        List<CommentDetail> comments = commentRepository.findByAuthorId(userId).stream()
                .map(comment -> new CommentDetail(comment.getId(), comment.getPostId(), comment.getAuthorId(), comment.getContent(), comment.getCreatedAt()))
                .collect(Collectors.toList());
        return new UserCommentsDto(comments);
    }
}
