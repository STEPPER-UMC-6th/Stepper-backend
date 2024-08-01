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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
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
    public ExerciseCardDto.ExerciseCardResponseDto getExerciseCardDetail(Long exerciseId) {

        ExerciseCard exerciseCard = exerciseCardRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseCardHandler(ErrorStatus.EXERCISE_CARD_NOT_FOUND));

        List<ExerciseStep> exerciseStepList = exerciseStepRepository.findAllByExerciseCard(exerciseCard);
        return ExerciseCardConverter.toDto(exerciseCard, exerciseStepList);
    }

    @Override
    public ExerciseCardDto.ExerciseCardResponseDto editExerciseCard(Long exerciseId, ExerciseCardDto.ExerciseCardRequestDto request) {

        ExerciseCard existingExerciseCard = exerciseCardRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseCardHandler(ErrorStatus.EXERCISE_CARD_NOT_FOUND));

        // 운동 카드 업데이트
        ExerciseCard newExerciseCard = ExerciseCardConverter.updateEntity(existingExerciseCard, request);

        // 기존 운동 단계 삭제
        exerciseStepRepository.deleteAllByExerciseCard(existingExerciseCard);

        // 운동 카드 단계 업데이트
        List<ExerciseStep> exerciseStepList = request.getStepList().stream()
                .map(exerciseStepRequestDto -> {
                    // 나만의 운동 찾기
                    MyExercise myExercise = myExerciseRepository.findById(exerciseStepRequestDto.getMyExerciseId())
                            .orElseThrow(() -> new MyExerciseHandler(ErrorStatus.MY_EXERCISE_NOT_FOUND));
                    // exerciseStep 저장
                    ExerciseStep exerciseStep = ExerciseStepConverter.toEntity(exerciseStepRequestDto, newExerciseCard, myExercise);
                    return exerciseStep;
                })
                .collect(Collectors.toList());

        newExerciseCard.setExerciseStepList(exerciseStepList);
        return ExerciseCardConverter.toDto(newExerciseCard, exerciseStepList);
    }

    @Override
    public List<ExerciseCardDto.ExerciseCardStatusResponseDto> getExerciseStatusByMonth(int month, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<ExerciseCard> exerciseCardList = exerciseCardRepository.findAllByMemberAndMonth(member, month);

        if(exerciseCardList.isEmpty()){
            throw new ExerciseCardHandler(ErrorStatus.EXERCISE_CARD_NOT_FOUND);
        }
        return exerciseCardList.stream().map(ExerciseCardConverter::toStatusResponseDto).collect(Collectors.toList());
    }


    @Override
    public List<ExerciseCardDto.ToDayExerciseResponseDto> getTodayExercises(LocalDate date, String memberEmail) {
     Member member = memberRepository.findByEmail(memberEmail)
             .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<ExerciseCard> exerciseCardList = exerciseCardRepository.findAllByMemberAndDate(member, date);
        List<ExerciseStep> exerciseStepList = new ArrayList<>();

        // 각 운동 카드에 대한 운동 단계 조회
        for (ExerciseCard exerciseCard : exerciseCardList) {
            List<ExerciseStep> stepsForCard = exerciseStepRepository.findAllByExerciseCard(exerciseCard);
            exerciseStepList.addAll(stepsForCard); // 각 카드의 운동 단계를 리스트에 추가
        }
        if(exerciseCardList.isEmpty()) throw new ExerciseCardHandler(ErrorStatus.EXERCISE_CARD_NOT_FOUND);

        return ExerciseCardConverter.toDayExerciseListDto(exerciseCardList, exerciseStepList);

    }
}
