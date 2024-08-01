package com.example.stepperbackend.service;

import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.web.dto.PostDto;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.web.dto.PostDto.PostRequestDto;
import com.example.stepperbackend.web.dto.PostDto.PostResponseDto;
import com.example.stepperbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostDto.PostResponseDto createPost(PostDto.PostRequestDto requestDto) {
        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthorId());
        post = postRepository.save(post);
        return new PostDto.PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthorId(), post.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("No post found with ID: " + postId));
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthorId(), post.getCreatedAt());
    }
}
