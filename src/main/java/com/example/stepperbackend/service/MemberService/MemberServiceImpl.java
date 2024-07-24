package com.example.stepperbackend.service.MemberService;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.web.converter.MemberConverter;
import com.example.stepperbackend.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public MemberDto.MemberResponseDto login(MemberDto.MemberLoginRequestDto dto) {
        Optional<Member> memberOptional = memberRepository.findByEmail(dto.getEmail());
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            if (bCryptPasswordEncoder.matches(dto.getPassword(), member.getPassword())) {
                MemberDto.MemberResponseDto response = MemberConverter.toDto(member);
                response.setPassword(null); // 비밀번호 제거
                return response;
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
