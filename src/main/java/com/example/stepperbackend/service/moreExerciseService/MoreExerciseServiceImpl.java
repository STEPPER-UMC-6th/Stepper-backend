package com.example.stepperbackend.service.moreExerciseService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MoreExercise;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.MoreExerciseRepository;
import com.example.stepperbackend.web.converter.MoreExerciseConverter;
import com.example.stepperbackend.web.dto.MoreExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MoreExerciseServiceImpl implements MoreExerciseService{

    final MemberRepository memberRepository;
    final MoreExerciseRepository moreExerciseRepository;

    @Override
    public MoreExerciseDto.MoreExerciseResponseDto exerciseAdd(MoreExerciseDto.MoreExerciseRequestDto dto, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        MoreExercise moreExercise = MoreExerciseConverter.toEntity(dto, member);
        moreExerciseRepository.save(moreExercise);
        MoreExerciseDto.MoreExerciseResponseDto response = MoreExerciseConverter.toDto(moreExercise);
        return response;
    }
}
