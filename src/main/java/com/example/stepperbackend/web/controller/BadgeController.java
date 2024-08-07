package com.example.stepperbackend.web.controller;


import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.badgeService.BadgeService;
import com.example.stepperbackend.web.dto.BadgeDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/badge")
@RequiredArgsConstructor
public class BadgeController {

    final BadgeService badgeService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "뱃지 조회 API",description = "뱃지 조회")
    @GetMapping
    public ApiResponse<List<BadgeDto.BadgeListDto>> getBadge(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getUsername(token);
        List<BadgeDto.BadgeListDto> response = badgeService.getBadge(email);
        return ApiResponse.onSuccess(response);
    }
}
