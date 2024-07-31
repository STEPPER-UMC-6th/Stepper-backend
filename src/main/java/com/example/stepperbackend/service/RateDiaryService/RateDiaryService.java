package com.example.stepperbackend.service.RateDiaryService;

import com.example.stepperbackend.web.dto.RateDiaryDto;



public interface RateDiaryService {

    public RateDiaryDto.RateDiaryWriteResponseDTO writeDiary(RateDiaryDto.RateDiaryWriteRequestDTO request, String memberEmail);

}
