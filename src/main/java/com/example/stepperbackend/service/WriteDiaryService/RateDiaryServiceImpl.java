package com.example.stepperbackend.service.WriteDiaryService;

import com.example.stepperbackend.converter.RateDiaryConverter;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.RateDiary;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.RateDiaryRepository;
import com.example.stepperbackend.web.dto.RateDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateDiaryServiceImpl implements RateDiaryService {

    private final MemberRepository memberRepository;
    private final RateDiaryRepository rateDiaryRepository;



    public RateDiaryDto.RateDiaryResponseDTO writeDiary(RateDiaryDto.RateDiaryRequestDTO request, String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found" + memberEmail));

        RateDiary rateDiary = RateDiaryConverter.toEntity(request, member);
        rateDiaryRepository.save(rateDiary);
        RateDiaryDto.RateDiaryResponseDTO response = RateDiaryConverter.toDto(rateDiary);
        return response;

    }
}
