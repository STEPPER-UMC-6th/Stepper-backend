package com.example.stepperbackend.service.badgeService;


import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.BadgeHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.converter.BadgeConverter;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.repository.BadgeCategoryRepository;
import com.example.stepperbackend.repository.BadgeRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.web.dto.BadgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class badgeServiceImpl implements BadgeService{

    final MemberRepository memberRepository;
    final BadgeRepository badgeRepository;
    final BadgeCategoryRepository badgeCategoryRepository;

    @Override
    public List<BadgeDto.BadgeListDto> getBadge(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        List<BadgeDto.BadgeListDto> badgeList = badgeCategoryRepository.findAll().stream()
                .map(category -> badgeRepository.findAllByBadgeCategoryAndMember(category, member))
                .filter(badges -> !badges.isEmpty())
                .map(badges -> BadgeConverter.toBadgeListDto(badges, badges.get(0).getBadgeCategory()))
                .collect(Collectors.toList());

        if(badgeList.isEmpty()) {
            throw new BadgeHandler(ErrorStatus.BADGE_NOT_FOUND);
        }
        return badgeList;
    }
}
