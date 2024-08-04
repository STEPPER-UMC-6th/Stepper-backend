package com.example.stepperbackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class likeResponseDto {
        private Long id;
        private Long memberId;
        private Long postId;
    }
}
