package com.stepper.stepperbackend.service;

import com.stepper.stepperbackend.domain.Post;
import com.stepper.stepperbackend.web.dto.PostDto.PostRequestDto;
import com.stepper.stepperbackend.web.dto.PostDto.PostResponseDto;
import com.stepper.stepperbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthorId());
        post = postRepository.save(post);
        return new PostResponseDto(post.getId(), post.getTitle(), post.getCreatedAt());
    }
}
