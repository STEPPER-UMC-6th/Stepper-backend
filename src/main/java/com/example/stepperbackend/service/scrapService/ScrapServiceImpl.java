package com.example.stepperbackend.service.scrapService;


import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
import com.example.stepperbackend.converter.ScrapConverter;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.mapping.Scrap;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.PostRepository;
import com.example.stepperbackend.repository.ScrapRepository;
import com.example.stepperbackend.web.dto.ScrapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapServiceImpl implements ScrapService {

    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;


    @Override
    public ScrapDto.ScrapResponseDto creatScrap(String email, Long postId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        if (scrapRepository.existsByMemberAndPost(member, post)) {
            throw new PostHandler(ErrorStatus.SCRAP_ALREADY_EXISTS);
        }

        Scrap scrap = ScrapConverter.toEntity(member, post);
        scrapRepository.save(scrap);
        return ScrapConverter.toDto(scrap);
    }

    @Override
    public void deleteScrap(String email, Long postId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        if(scrapRepository.existsByMemberAndPost(member, post)) {
            scrapRepository.deleteByMemberAndPost(member, post);
        }
    }
}
