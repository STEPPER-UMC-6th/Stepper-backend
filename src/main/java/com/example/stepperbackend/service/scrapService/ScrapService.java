package com.example.stepperbackend.service.scrapService;

import com.example.stepperbackend.domain.mapping.Scrap;
import com.example.stepperbackend.web.dto.ScrapDto;

public interface ScrapService {

    ScrapDto.ScrapResponseDto creatScrap(String email, Long postId);

    void deleteScrap(String email, Long postId);
}
