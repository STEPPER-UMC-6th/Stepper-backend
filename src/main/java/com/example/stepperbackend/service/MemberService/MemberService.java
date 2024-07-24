package com.example.stepperbackend.service.MemberService;

import com.example.stepperbackend.web.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface MemberService {
    MemberDto.MemberResponseDto signup(MemberDto.MemberSignupRequestDto dto);
}
