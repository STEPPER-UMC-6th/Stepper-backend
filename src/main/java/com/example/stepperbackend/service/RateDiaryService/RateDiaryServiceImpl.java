package com.example.stepperbackend.service.RateDiaryService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.*;
import com.example.stepperbackend.converter.BadgeConverter;
import com.example.stepperbackend.converter.RateDiaryConverter;
import com.example.stepperbackend.domain.*;
import com.example.stepperbackend.domain.mapping.Badge;
import com.example.stepperbackend.repository.*;
import com.example.stepperbackend.web.dto.RateDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class RateDiaryServiceImpl implements RateDiaryService {

    private final MemberRepository memberRepository;
    private final RateDiaryRepository rateDiaryRepository;
    private final ExerciseCardRepository exerciseCardRepository;
    private final BadgeCategoryRepository badgeCategoryRepository;
    private final BadgeRepository badgeRepository;

    public RateDiaryDto.RateDiaryWriteResponseDTO writeDiary(RateDiaryDto.RateDiaryWriteRequestDTO request, String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        ExerciseCard exerciseCard = exerciseCardRepository.findById(request.getExerciseCardId())
                .orElseThrow(() -> new MyExerciseHandler(ErrorStatus.MY_EXERCISE_NOT_FOUND));

        if(!exerciseCard.getMember().getEmail().equals(memberEmail)) {
            throw new RateDiaryHandler(ErrorStatus.EXERCISE_CARD_DOES_NOT_BELONG_TO_USER);
        }

        RateDiary rateDiary = RateDiaryConverter.toEntity(request, exerciseCard, member);
        rateDiaryRepository.save(rateDiary);

        // 첫 운동 완료
        exerciseCard.setStatus(true);

        if(exerciseCardRepository.getCountTureStatusByMember(member) == 1) {
            BadgeCategory badgeCategory = badgeCategoryRepository.findById(1L)
                            .orElseThrow(() -> new BadgeHandler(ErrorStatus.BADGE_CATEGORY_NOT_FOUND));
            Badge badge = BadgeConverter.toEntity("첫 오늘의 운동 완료", member, badgeCategory);
            badgeRepository.save(badge);
        }
        return RateDiaryConverter.toDto(rateDiary);
    }



    public List<RateDiary> checkRateDiary (String memberEmail) {

        List<RateDiary> rateDiaries = rateDiaryRepository.findAll().stream()
                .filter(rateDiary -> rateDiary.getMember().getEmail().equals(memberEmail)).toList();

        if(rateDiaries.isEmpty()) {throw new RateDiaryHandler(ErrorStatus.RATE_DIARY_NOT_FOUND);}

        return rateDiaries;
    }
}



