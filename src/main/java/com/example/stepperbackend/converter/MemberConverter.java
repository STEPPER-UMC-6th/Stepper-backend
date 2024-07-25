package com.example.stepperbackend.converter;


import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.web.dto.MemberDto;

import java.time.LocalDate;

public class MemberConverter {

    public static Member toEntity(MemberDto.MemberSignupRequestDto dto) {
        return Member.builder()
                .name(dto.getName())
                .nickName(dto.getNickName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .profileImage(dto.getProfileImage())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .communityAlarm(dto.isCommunityAlarm())
                .exerciseAlarm(dto.isExerciseAlarm())
                .emailAgree(dto.isEmailAgree())
                .useAgree(dto.isUseAgree())
                .perAgree(dto.isPerAgree())
//                .createdAt(LocalDate.now())
//                .updatedAt(LocalDate.now())
                .build();
    }

    public static MemberDto.MemberResponseDto toDto(Member member) {
        MemberDto.MemberResponseDto dto = new MemberDto.MemberResponseDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setNickName(member.getNickName());
        dto.setEmail(member.getEmail());
        dto.setPassword(member.getPassword());
        dto.setProfileImage(member.getProfileImage());
        dto.setHeight(member.getHeight());
        dto.setWeight(member.getWeight());
        dto.setCommunityAlarm(member.isCommunityAlarm());
        dto.setExerciseAlarm(member.isExerciseAlarm());
        dto.setEmailAgree(member.isEmailAgree());
        dto.setUseAgree(member.isUseAgree());
        dto.setPerAgree(member.isPerAgree());
//        dto.setCreatedAt(member.getCreatedAt());
//        dto.setUpdatedAt(member.getUpdatedAt());
        return dto;
    }
}
