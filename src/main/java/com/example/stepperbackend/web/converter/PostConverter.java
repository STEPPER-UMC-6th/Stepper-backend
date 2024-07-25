package com.example.converter;

package com.example.stepperbackend.web.converter;
        package com.example.stepperbackend.web.dto.PostDto;

import java.time.format.DateTimeFormatter;

public class PostConverter {

    public static PostResponseDto toPostResponseDto(Post post) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .createdAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
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
