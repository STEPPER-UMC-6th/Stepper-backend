package com.example.stepperbackend.service.likeService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
import com.example.stepperbackend.converter.LikeConverter;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.mapping.Likes;
import com.example.stepperbackend.repository.LikeRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.PostRepository;
import com.example.stepperbackend.web.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    public LikeDto.likeResponseDto createLike(String email, Long postId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        if(likeRepository.existsByMemberAndPost(member, post)) {
            throw new PostHandler(ErrorStatus.LIKE_ALREADY_EXISTS);
        }

        Likes likes = LikeConverter.toEntity(member, post);
        likeRepository.save(likes);
        return LikeConverter.toDto(likes);
    }

    @Override
    public void deleteLike(String email, Long postId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        if(likeRepository.existsByMemberAndPost(member, post)) {
            likeRepository.deleteByMemberAndPost(member, post);
        }
    }
}
