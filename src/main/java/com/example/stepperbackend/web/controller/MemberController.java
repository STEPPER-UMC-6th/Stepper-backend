package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.apiPayload.code.status.SuccessStatus;
import com.example.stepperbackend.jwt.JWTUtil;
import com.example.stepperbackend.service.MemberService.MemberService;
import com.example.stepperbackend.web.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Operation(summary = "로그인 API", description = "사용자 로그인")
//    @PostMapping("/login")
//    public ApiResponse<MemberDto.MemberResponseDto> login(HttpServletRequest request, @RequestBody MemberDto.MemberLoginRequestDto dto) {
//        MemberDto.MemberResponseDto response = memberService.login(dto);
//        HttpSession session = request.getSession(true);
//        session.setAttribute("user", response);
//        return ApiResponse.onSuccess(response);
//    }

//    @Operation(summary = "로그인 API", description = "사용자 로그인")
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody MemberDto.MemberLoginRequestDto dto) {
//        // 사용자 인증
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // JWT 토큰 생성
//        String jwt = jwtUtil.createJwt(dto.getEmail(), "ROLE_USER", 86400000L); // 1 day in milliseconds
//
//        // JWT 토큰 반환
//        return ApiResponse.onSuccess(jwt);
//    }

//    @Operation(summary = "로그인 API", description = "사용자 로그인")
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody MemberDto.MemberLoginRequestDto dto) {
//        log.info("Login request received for email: {}", dto.getEmail());
//        try {
//            // 사용자 인증
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("Authentication successful for email: {}", dto.getEmail());
//
//            // JWT 토큰 생성
//            String jwt = jwtUtil.createJwt(dto.getEmail(), "ROLE_USER", 86400000L); // 1 day in milliseconds
//            log.info("JWT token generated for email: {}", dto.getEmail());
//
//            // JWT 토큰 반환
//            log.info("Login successful for email: {}", dto.getEmail());
//            return ApiResponse.onSuccess(jwt);
//        } catch (Exception e) {
//            log.error("Login failed for email: {}", dto.getEmail(), e);
//            throw e;
//        }
//    }

//    @Operation(summary = "로그인 API", description = "사용자 로그인")
//    @PostMapping("/login")
//    public ApiResponse<MemberDto.MemberResponseDto> login(@Valid @RequestBody MemberDto.MemberLoginRequestDto dto) {
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String jwt = jwtUtil.createJwt(authentication.getName(), 60 * 60 * 10L);
//            MemberDto.MemberResponseDto response = new MemberDto.MemberResponseDto();
//            response.setToken(jwt); // 토큰을 응답 DTO에 설정
//            return ApiResponse.onSuccess(response);
//        } catch (Exception e) {
//            return ApiResponse.onFailure("AUTH_ERROR", "Authentication failed", null);
//        }
//    }

//    @Operation(summary = "로그인 API", description = "사용자 로그인")
//    @PostMapping("/login")
//    public ApiResponse<String> login(@Valid @RequestBody MemberDto.MemberLoginRequestDto dto) {
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String jwt = jwtUtil.createJwt(authentication.getName(), 60 * 60 * 10L); // authority 부분 제거
//            return ApiResponse.onSuccess(jwt); // 토큰을 응답으로 반환
//        } catch (Exception e) {
//            return ApiResponse.onFailure("AUTH_ERROR", "Authentication failed", null);
//        }
//    }

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
    public ApiResponse<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ApiResponse.onSuccess(null);
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