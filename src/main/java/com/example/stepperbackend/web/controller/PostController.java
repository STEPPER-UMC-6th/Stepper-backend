package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.PostService.PostService;
import com.example.stepperbackend.web.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class PostController {

    private final JWTUtil jwtUtil;
    private final PostService postService;

    @Operation(summary = "게시글 작성 API", description = "사용자 게시글 작성")
    @PostMapping("/write")
    public ApiResponse<PostDto.PostResponseDto> createPost(@RequestBody PostDto.PostRequestDto postRequestDto, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        PostDto.PostResponseDto response = postService.createPost(postRequestDto, email);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "내가 작성한 글 조회 API", description = "내가 작성한 글 조회")
    @GetMapping("/{member_id}/posts")
    public ApiResponse<List<PostDto.PostResponseDto>> getPostsList(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        List<PostDto.PostResponseDto> response = postService.getPostsList(email);
        return ApiResponse.onSuccess(response);
    }
}