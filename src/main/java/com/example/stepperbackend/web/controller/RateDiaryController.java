package com.example.stepperbackend.web.controller;

import com.example.stepperbackend.apiPayload.ApiResponse;
import com.example.stepperbackend.service.RateDiaryService.RateDiaryService;
import com.example.stepperbackend.web.dto.RateDiaryDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/RateDiary")
public class RateDiaryController {

    private final RateDiaryService rateDiaryService;

    @PostMapping("/write")
    @Operation(summary = "평가일지 작성 API",description = "평가일지 작성")
    public ApiResponse<RateDiaryDto.RateDiaryResponseDTO> Write(@RequestBody @Valid RateDiaryDto.RateDiaryRequestDTO request){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        RateDiaryDto.RateDiaryResponseDTO rateDiary = rateDiaryService.writeDiary(request, memberId);
        return ApiResponse.onSuccess(rateDiary);
    }
}
