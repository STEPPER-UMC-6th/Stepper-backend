package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.mapping.Scrap;
import com.example.stepperbackend.web.dto.ScrapDto;

public class ScrapConverter {

    public static Scrap toEntity(Member member, Post post) {
        return Scrap.builder()
                .member(member)
                .post(post)
                .build();
    }

    public static ScrapDto.ScrapResponseDto toDto(Scrap scrap) {
        return ScrapDto.ScrapResponseDto.builder()
                .id(scrap.getId())
                .postId(scrap.getPost().getId())
                .memberId(scrap.getMember().getId())
                .build();
    }

}
