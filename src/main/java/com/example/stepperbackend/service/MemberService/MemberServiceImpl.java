package com.example.stepperbackend.service.MemberService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.converter.MemberConverter;
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

        // 이메일 중복 체크
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new MemberHandler(ErrorStatus.EMAIL_ALREADY_EXISTS);
        }

        Member member = MemberConverter.toEntity(dto);
        member.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        member = memberRepository.save(member);
        MemberDto.MemberResponseDto response = MemberConverter.toDto(member);
        response.setPassword(null); // 비밀번호 제거
        return response;
    }
    @Override
    public void deleteMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
        } else {
            throw new RuntimeException("Member not found");
        }
    }

    @Override
    public MemberDto.MemberResponseDto getMemberInfo(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            Member memberEntity = member.get();
            return MemberDto.MemberResponseDto.builder()
                    .id(memberEntity.getId())
                    .name(memberEntity.getName())
                    .nickName(memberEntity.getNickName())
                    .email(memberEntity.getEmail())
                    .profileImage(memberEntity.getProfileImage())
                    .height(memberEntity.getHeight())
                    .weight(memberEntity.getWeight())
                    .communityAlarm(memberEntity.isCommunityAlarm())
                    .exerciseAlarm(memberEntity.isExerciseAlarm())
                    .emailAgree(memberEntity.isEmailAgree())
                    .useAgree(memberEntity.isUseAgree())
                    .perAgree(memberEntity.isPerAgree())
//                    .createdAt(memberEntity.getCreatedAt())
//                    .updatedAt(memberEntity.getUpdatedAt())
                    .build();
        } else {
            throw new RuntimeException("Member not found");
        }
    }
}
