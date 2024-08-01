package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.service.CommentService;
import com.example.stepperbackend.web.dto.CommentDto.CommentRequestDto;
import com.example.stepperbackend.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.createComment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
