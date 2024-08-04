package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.mapping.Likes;
import com.example.stepperbackend.web.dto.LikeDto;

public class LikeConverter {

    public static LikeDto.likeResponseDto toDto(Likes likes){
        return LikeDto.likeResponseDto.builder()
                .id(likes.getId())
                .memberId(likes.getMember().getId())
                .postId(likes.getPost().getId())
                .build();
    }

    public static Likes toEntity(Member member, Post post) {
        return Likes.builder()
                .member(member)
                .post(post)
                .build();
    }
}
