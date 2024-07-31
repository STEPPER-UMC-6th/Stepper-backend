package com.example.stepperbackend.service.moreExerciseService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.ExerciseHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MoreExercise;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.MoreExerciseRepository;
import com.example.stepperbackend.converter.MoreExerciseConverter;
import com.example.stepperbackend.web.dto.MoreExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MoreExerciseServiceImpl implements MoreExerciseService {

    final MemberRepository memberRepository;
    final MoreExerciseRepository moreExerciseRepository;

    @Override
    public MoreExerciseDto.MoreExerciseResponseDto exerciseAdd(MoreExerciseDto.MoreExerciseRequestDto dto, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        MoreExercise moreExercise = MoreExerciseConverter.toEntity(dto, member);
        moreExerciseRepository.save(moreExercise);
        return MoreExerciseConverter.toDto(moreExercise);
    }

    @Override
    public List<MoreExerciseDto.MoreExerciseResponseDto> getMoreExerciseList(String email, LocalDate date) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<MoreExercise> moreExerciseList = Optional.ofNullable(moreExerciseRepository.findAllByMemberAndDate(member, date))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ExerciseHandler(ErrorStatus.MORE_EXERCISE_NOT_FOUND));
        return moreExerciseList.stream().map(MoreExerciseConverter::toDto).collect(Collectors.toList());
    }
}
