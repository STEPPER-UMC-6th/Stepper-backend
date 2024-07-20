package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.apiPayload.code.status.SuccessStatus;
import com.example.stepperbackend.service.MemberService.MemberService;
import com.example.stepperbackend.web.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

//    @Operation(summary = "회원가입 API",description = "사용자 회원가입")
//    @PostMapping("/signup")
//    public ResponseEntity<MemberDto.MemberResponseDto> signup(@RequestBody MemberDto.MemberSignupRequestDto dto) {
//        MemberDto.MemberResponseDto response = memberService.signup(dto);
//        return ResponseEntity.ok(response);
//        //return ResponseEntity.ok(ApiResponse.onSuccess(response));
//    }

    @Operation(summary = "회원가입 API",description = "사용자 회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<MemberDto.MemberResponseDto>> signup(@RequestBody MemberDto.MemberSignupRequestDto dto) {
        MemberDto.MemberResponseDto response = memberService.signup(dto);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

//    @PostMapping("/login")
//    public ResponseEntity<MemberDto.MemberResponseDto> login(@RequestBody MemberDto.MemberLoginRequestDto dto) {
//        MemberDto.MemberResponseDto response = memberService.login(dto);
//        return ResponseEntity.ok(response);
//    }
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