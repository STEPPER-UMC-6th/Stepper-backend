package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.WeeklyMission;
import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.web.dto.PostDto;

public class PostConverter {

    public static Post toEntity(PostDto.PostRequestDto dto, Member member, WeeklyMission weeklyMission) {
        return Post.builder()
                .imageUrl(dto.getImageUrl())
                .title(dto.getTitle())
                .body(dto.getBody())
                .bodyPart(BodyPart.valueOf(dto.getBodyPart().toString()))
                .subCategory(dto.getSubCategory())
                .member(member)
                .weeklyMission(weeklyMission)
                .build();
    }

    public static PostDto.PostResponseDto toDto(Post post) {
        return PostDto.PostResponseDto.builder()
                .id(post.getId())
                .imageUrl(post.getImageUrl())
                .title(post.getTitle())
                .body(post.getBody())
                .bodyPart(post.getBodyPart().toString())
                .authorEmail(post.getMember().getEmail())
                .subCategory(post.getSubCategory())
                //.weeklyMissionTitle(post.getWeeklyMission() != null ? post.getWeeklyMission().getMissionTitle() : null)
                .weeklyMissionTitle(post.getWeeklyMission() != null ? post.getWeeklyMission().getMissionTitle() : null)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostDto.PostViewDto toViewDto(Post post, int likes, int scraps, int commentsCount) {
        return PostDto.PostViewDto.builder()
                .id(post.getId())
                .imageUrl(post.getImageUrl())
                .title(post.getTitle())
                .body(post.getBody())
                .bodyPart(post.getBodyPart().toString())
                .authorEmail(post.getMember().getEmail())
                .subCategory(post.getSubCategory())
                //
                .likes(likes)
                .scraps(scraps)
                .commentsCount(commentsCount)
                //.weeklyMissionTitle(post.getWeeklyMission() != null ? post.getWeeklyMission().getMissionTitle() : null)
                .weeklyMissionTitle(post.getWeeklyMission() != null ? post.getWeeklyMission().getMissionTitle() : null)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}