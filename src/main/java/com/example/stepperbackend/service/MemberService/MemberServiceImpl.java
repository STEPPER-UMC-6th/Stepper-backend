package com.example.stepperbackend.service.MemberService;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.web.converter.MemberConverter;
import com.example.stepperbackend.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberDto.MemberResponseDto signup(MemberDto.MemberSignupRequestDto dto) {
        Member member = MemberConverter.toEntity(dto);
        member.setPassword(bCryptPasswordEncoder.encode((dto.getPassword())));
        member = memberRepository.save(member);
        return MemberConverter.toDto(member);
    }
}
