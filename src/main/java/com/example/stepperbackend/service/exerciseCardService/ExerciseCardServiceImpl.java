package com.example.stepperbackend.service.exerciseCardService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.ExerciseCardHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MyExerciseHandler;
import com.example.stepperbackend.converter.ExerciseCardConverter;
import com.example.stepperbackend.converter.ExerciseStepConverter;
import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.ExerciseStep;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.repository.ExerciseCardRepository;
import com.example.stepperbackend.repository.ExerciseStepRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.MyExerciseRepository;
import com.example.stepperbackend.web.dto.ExerciseCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExerciseCardServiceImpl implements ExerciseCardService {

    final MemberRepository memberRepository;
    final ExerciseCardRepository exerciseCardRepository;
    final ExerciseStepRepository exerciseStepRepository;
    final MyExerciseRepository myExerciseRepository;

    @Override
    public ExerciseCardDto.ExerciseCardResponseDto addExerciseCard(ExerciseCardDto.ExerciseCardRequestDto request, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 운동 카드 저장
        ExerciseCard exerciseCard = ExerciseCardConverter.toEntity(request, member);
        exerciseCardRepository.save(exerciseCard);

        // 운동 카드 단계
        List<ExerciseStep> exerciseStepList = request.getStepList().stream()
                .map(exerciseStepRequestDto -> {
                    // 나만의 운동 찾기
                    MyExercise myExercise = myExerciseRepository.findById(exerciseStepRequestDto.getMyExerciseId())
                            .orElseThrow(() -> new MyExerciseHandler(ErrorStatus.MY_EXERCISE_NOT_FOUND));
                    // exerciseStep 저장
                    ExerciseStep exerciseStep = ExerciseStepConverter.toEntity(exerciseStepRequestDto, exerciseCard, myExercise);
                    return exerciseStepRepository.save(exerciseStep);
                })
                .collect(Collectors.toList());

        exerciseCard.setExerciseStepList(exerciseStepList);

        return ExerciseCardConverter.toDto(exerciseCard, exerciseStepList);
    }

    @Override
    public ExerciseCardDto.ExerciseCardResponseDto getExerciseCardDetail(Long exerciseId, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        ExerciseCard exerciseCard = exerciseCardRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseCardHandler(ErrorStatus.EXERCISE_CARD_NOT_FOUND));

        List<ExerciseStep> exerciseStepList = exerciseStepRepository.findAllByExerciseCard(exerciseCard);
        return ExerciseCardConverter.toDto(exerciseCard, exerciseStepList);
    }

}
