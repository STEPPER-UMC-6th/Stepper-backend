package com.example.stepperbackend.converter;


import com.example.stepperbackend.domain.ExerciseCard;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.RateDiary;
import com.example.stepperbackend.repository.ExerciseCardRepository;
import com.example.stepperbackend.web.dto.RateDiaryDto;

import java.time.LocalDate;

public class RateDiaryConverter {

    public static RateDiary toEntity(RateDiaryDto.RateDiaryWriteRequestDTO dto, ExerciseCard exerciseCard, Member member) {


        return RateDiary.builder()
                .exerciseCard(exerciseCard)
                .conditionRate(dto.getConditionRate())
                .painRate(dto.getPainRate())
                .painImage(dto.getPainImage())
                .painMemo(dto.getPainMemo())
                .member(member)
                .date(LocalDate.now())
                .build();
    }

    public static RateDiaryDto.RateDiaryWriteResponseDTO toDto(RateDiary rateDiary) {
        return RateDiaryDto.RateDiaryWriteResponseDTO.builder()
                .rate_id(rateDiary.getId())
                .build();
    }
}
