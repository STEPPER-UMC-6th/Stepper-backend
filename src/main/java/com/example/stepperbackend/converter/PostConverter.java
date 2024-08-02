package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.web.dto.PostDto;

public class PostConverter {

    public static Post toEntity(PostDto.PostRequestDto dto, Member member) {
        return Post.builder()
                .imageUrl(dto.getImageUrl())
                .title(dto.getTitle())
                .body(dto.getBody())
                .categoryId(dto.getCategoryId())
                .subCategory(dto.getSubCategory())
                .member(member)
                .build();
    }

    public static PostDto.PostResponseDto toDto(Post post) {
        return PostDto.PostResponseDto.builder()
                .id(post.getId())
                .imageUrl(post.getImageUrl())
                .title(post.getTitle())
                .body(post.getBody())
                .authorEmail(post.getMember().getEmail())
                .categoryId(post.getCategoryId())
                .subCategory(post.getSubCategory())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}