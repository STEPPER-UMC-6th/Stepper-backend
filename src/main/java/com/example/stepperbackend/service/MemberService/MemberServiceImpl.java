package com.example.stepperbackend.service.MemberService;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.web.converter.MemberConverter;
import com.example.stepperbackend.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberDto.MemberResponseDto signup(MemberDto.MemberSignupRequestDto dto) {
        Member member = MemberConverter.toEntity(dto);
        member.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        member = memberRepository.save(member);
        MemberDto.MemberResponseDto response = MemberConverter.toDto(member);
        response.setPassword(null); // 비밀번호 제거
        return response;
    }

}
