package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.WeeklyMission;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.PostRepository;
import com.example.stepperbackend.repository.WeeklyMissionRepository;
import com.example.stepperbackend.web.dto.PostDto;
import com.example.stepperbackend.converter.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final WeeklyMissionRepository weeklyMissionRepository;

    @Override
    public PostDto.PostResponseDto createPost(PostDto.PostRequestDto postRequestDto, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        WeeklyMission weeklyMission = null;
        if (postRequestDto.getWeeklyMissionId() != null) {
            weeklyMission = weeklyMissionRepository.findById(postRequestDto.getWeeklyMissionId()).orElse(null);
        }


        Post post = PostConverter.toEntity(postRequestDto, member, weeklyMission);
        post = postRepository.save(post);

        return PostConverter.toDto(post);
    }
}

