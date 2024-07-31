package com.example.stepperbackend.service.RateDiaryService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MyExerciseHandler;
import com.example.stepperbackend.apiPayload.exception.handler.RateDiaryHandler;
import com.example.stepperbackend.converter.RateDiaryConverter;
import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.RateDiary;
import com.example.stepperbackend.repository.ExerciseCardRepository;
import com.example.stepperbackend.repository.ExerciseStepRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.RateDiaryRepository;
import com.example.stepperbackend.web.dto.RateDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateDiaryServiceImpl implements RateDiaryService {

    private final MemberRepository memberRepository;
    private final RateDiaryRepository rateDiaryRepository;
    private final ExerciseCardRepository exerciseCardRepository;

    public RateDiaryDto.RateDiaryWriteResponseDTO writeDiary(RateDiaryDto.RateDiaryWriteRequestDTO request, String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        ExerciseCard exerciseCard = exerciseCardRepository.findById(request.getExerciseCardId())
                .orElseThrow(() -> new MyExerciseHandler(ErrorStatus.MY_EXERCISE_NOT_FOUND));

        if(!exerciseCard.getMember().getEmail().equals(memberEmail)) {
            throw new RateDiaryHandler(ErrorStatus.EXERCISE_CARD_DOES_NOT_BELONG_TO_USER);
        }

        RateDiary rateDiary = RateDiaryConverter.toEntity(request,exerciseCard, member);
        rateDiaryRepository.save(rateDiary);
        return RateDiaryConverter.toDto(rateDiary);
    }
}

//    public List<RateDiary> checkRateDiary(RateDiaryDto.RateDiaryCheckRequestDTO request, String memberEmail) {
//        Member member = memberRepository.findByEmail(memberEmail)
//                .orElseThrow(() -> new IllegalArgumentException("User not found" + memberEmail));
//
//        RateDiary rateDiary = RateDiaryConverter.toEntity(request, member);
//
//
//    }

