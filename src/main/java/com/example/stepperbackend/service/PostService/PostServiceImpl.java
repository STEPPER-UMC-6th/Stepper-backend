package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<PostDto.PostResponseDto> getPostsList(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<Post> postList = postRepository.findAllByMember(member);

        if(postList.isEmpty()) {
            throw new PostHandler(ErrorStatus.MY_POST_LIST_NOT_FOUND);
        }

        return postList.stream().map(PostConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public PostDto.PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostConverter.toDto(post);
    }
}

