package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.web.dto.PostDto.PostRequestDto;
import com.example.stepperbackend.web.dto.PostDto.PostResponseDto;
import com.example.stepperbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        PostResponseDto responseDto = postService.createPost(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 게시글 조회 API
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.ok(responseDto);
    }
/*
    //게시글 전체 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts(){
        List<PostResponseDto> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
        }*/
    }

