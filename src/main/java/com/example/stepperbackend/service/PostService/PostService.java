package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.repository.LikeRepository;
import com.example.stepperbackend.web.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto.PostResponseDto createPost(PostDto.PostRequestDto postRequestDto, String email);

    List<PostDto.PostViewDto> getPostsList(String email);

    PostDto.PostViewDto getPost(Long postId, String email);

    List<PostDto.PostViewDto> getCommentsList(String email);

}
