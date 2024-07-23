package com.example.stepperbackend.web.dto;

import lombok.Data;

import java.time.LocalDate;

public class MemberDto {

    @Data
    public static class MemberResponseDto {
        private Long id;
        private String name;
        private String nickName;
        private String email;
        private String password;
        private String profileImage;
        private Long height;
        private Long weight;
        private boolean communityAlarm;
        private boolean exerciseAlarm;
        private boolean emailAgree;
        private boolean useAgree;
        private boolean perAgree;
        private LocalDate createdAt;
        private LocalDate updatedAt;
    }

    @Data
    public static class MemberSignupRequestDto {
        private String name;
        private String nickName;
        private String email;
        private String password;
        private String profileImage;
        private Long height;
        private Long weight;
        private boolean communityAlarm;
        private boolean exerciseAlarm;
        private boolean emailAgree;
        private boolean useAgree;
        private boolean perAgree;
    }

    @Data
    public static class MemberLoginRequestDto {
        private String email;
        private String password;
    }
}
