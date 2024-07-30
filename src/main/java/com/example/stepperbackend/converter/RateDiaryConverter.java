package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.RateDiary;
import com.example.stepperbackend.web.dto.RateDiaryDto;

import java.time.LocalDate;

public class RateDiaryConverter {

    public static RateDiary toEntity(RateDiaryDto.RateDiaryRequestDTO dto, Member member) {
        return RateDiary.builder()
                .conditionRate(dto.getConditionRate())
                .pain_rate(dto.getPainRate())
                .painImage(dto.getPainImage())
                .painMemo(dto.getPainMemo())
                .member(member)
                .date(LocalDate.now())
                .build();
    }

    public static RateDiaryDto.RateDiaryResponseDTO toDto(RateDiary rateDiary) {
        return RateDiaryDto.RateDiaryResponseDTO.builder()
                .rate_id(rateDiary.getId())
                .build();
    }
}
