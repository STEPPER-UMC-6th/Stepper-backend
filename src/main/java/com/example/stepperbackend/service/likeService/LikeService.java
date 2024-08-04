package com.example.stepperbackend.service.likeService;

import com.example.stepperbackend.web.dto.LikeDto;

public interface LikeService {

    LikeDto.likeResponseDto createLike(String email, Long postId);
}
