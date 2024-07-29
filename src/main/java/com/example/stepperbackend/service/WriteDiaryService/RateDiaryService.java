package com.example.stepperbackend.service.WriteDiaryService;

import com.example.stepperbackend.web.dto.RateDiaryDto;

public interface RateDiaryService {

    public RateDiaryDto.RateDiaryResponseDTO writeDiary(RateDiaryDto.RateDiaryRequestDTO request, String memberEmail);

}
