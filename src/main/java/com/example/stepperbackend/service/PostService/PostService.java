package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.web.dto.PostDto;

public interface PostService {
    PostDto.PostResponseDto createPost(PostDto.PostRequestDto postRequestDto, String email);
}