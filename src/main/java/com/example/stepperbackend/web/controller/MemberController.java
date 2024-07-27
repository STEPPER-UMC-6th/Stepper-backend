package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.MemberService.MemberService;
import com.example.stepperbackend.web.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    private final AuthenticationManager authenticationManager;


    private final JWTUtil jwtUtil;


    @Operation(summary = "회원가입 API",description = "사용자 회원가입")
    @PostMapping("/signup")
    public ApiResponse<MemberDto.MemberResponseDto> signup(@RequestBody MemberDto.MemberSignupRequestDto dto) {
        System.out.println("MemberController.signup");
        MemberDto.MemberResponseDto response = memberService.signup(dto);
        return ApiResponse.onSuccess(response);
    }


    @Operation(summary = "로그인 API", description = "사용자 로그인")
    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody MemberDto.MemberLoginRequestDto dto) {
        try {
            log.info("로그인 시도: {}", dto.getEmail());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info("사용자 인증 성공: {}", authentication.getPrincipal());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String username = authentication.getName();
            log.info("인증된 사용자 이름: {}", username);

            String jwt = jwtUtil.createJwt(username, 60 * 60 * 10L);
            log.info("생성된 JWT: {}", jwt);

            return ApiResponse.onSuccess(jwt);
        } catch (Exception e) {
            log.error("인증 실패", e);
            return ApiResponse.onFailure("AUTH_ERROR", "Authentication failed", null);
        }
    }

    @Operation(summary = "로그아웃 API", description = "사용자 로그아웃")
    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_OK);
        return ApiResponse.onSuccess(null);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteMember(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String email = jwtUtil.getUsername(token);

            memberService.deleteMember(email);
            SecurityContextHolder.clearContext();
            return ApiResponse.onSuccess(null);
        } catch (Exception e) {
            log.error("회원 탈퇴 실패", e);
            return ApiResponse.onFailure("DELETE_ERROR", "Failed to delete member", null);
        }
    }

//
//    @GetMapping("/{memberId}")
//    public ResponseEntity<MemberDto.MemberResponseDto> getUserInfo(@PathVariable Long memberId) {
//        MemberDto.MemberResponseDto response = memberService.getUserInfo(memberId);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{memberId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long memberId, @RequestBody String password) {
//        memberService.deleteUser(memberId, password);
//        return ResponseEntity.noContent().build();
//    }
}