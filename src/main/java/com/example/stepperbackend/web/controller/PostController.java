package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.web.dto.PostDto.PostRequestDto;
import com.example.stepperbackend.web.dto.PostDto.PostResponseDto;
import com.example.stepperbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        PostResponseDto responseDto = postService.createPost(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
