package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.service.CommentService.CommentService;
import com.example.stepperbackend.web.dto.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성 API", description = "댓글 작성")
    @PostMapping("/write")
    public ApiResponse<CommentDto.CommentResponseDto> write(@RequestBody @Valid CommentDto.CommentRequestDto request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        CommentDto.CommentResponseDto response = commentService.writeComment(request, memberId);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "대댓글 작성 API", description = "대댓글 작성")
    @PostMapping("/reply")
    public ApiResponse<CommentDto.CommentResponseDto> reply(@RequestBody @Valid CommentDto.ReplyRequestDto request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        CommentDto.CommentResponseDto response = commentService.writeReply(request, memberId);
        return ApiResponse.onSuccess(response);
    }
}
