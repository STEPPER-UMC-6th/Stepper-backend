package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.web.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    PostDto.PostResponseDto createPost(PostDto.PostRequestDto postRequestDto, String email);

    List<PostDto.PostResponseDto> getPostsList(String email);


}