package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.BadgeCategory;
import com.example.stepperbackend.domain.mapping.Badge;
import com.example.stepperbackend.web.dto.BadgeDto;

import java.util.List;
import java.util.stream.Collectors;

public class BadgeConverter {

    public static BadgeDto.BadgeResponseDto toBadgeDto(Badge badge){
        return BadgeDto.BadgeResponseDto.builder()
                .id(badge.getId())
                .badgeName(badge.getBadgeName())
                .explanation(badge.getExplanation())
                .build();
    }

    public static BadgeDto.BadgeListDto toBadgeListDto(List<Badge> badgeList, BadgeCategory badgeCategory){

        List<BadgeDto.BadgeResponseDto> badgeDtoList = badgeList.stream()
                .map(BadgeConverter::toBadgeDto).collect(Collectors.toList());

        return BadgeDto.BadgeListDto.builder()
                .id(badgeCategory.getId())
                .categoryName(badgeCategory.getName())
                .list(badgeDtoList)
                .build();
    }

}
