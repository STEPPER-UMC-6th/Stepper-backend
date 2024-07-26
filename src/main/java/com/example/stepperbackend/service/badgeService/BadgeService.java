package com.example.stepperbackend.service.badgeService;

import com.example.stepperbackend.web.dto.BadgeDto;

import java.util.List;

public interface BadgeService {

    List<BadgeDto.BadgeListDto> getBadge(String email);
}
