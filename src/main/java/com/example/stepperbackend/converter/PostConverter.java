package com.example.stepperbackend.converter;
import com.example.stepperbackend.web.dto.PostDto;
import com.example.stepperbackend.web.dto.PostDto.PostRequestDto;
import com.example.stepperbackend.web.dto.PostDto.PostResponseDto;


import com.example.stepperbackend.domain.Post;
//
import java.time.format.DateTimeFormatter;

public class PostConverter {

    public static PostResponseDto toPostResponseDto(Post post) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static Post toPost(PostRequestDto postRequestDto) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .authorId(postRequestDto.getAuthorId())
                .build();
    }
}