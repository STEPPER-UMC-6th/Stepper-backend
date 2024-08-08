package com.example.stepperbackend.service.PostService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.domain.WeeklyMission;
import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.repository.*;
import com.example.stepperbackend.service.badgeService.BadgeService;
import com.example.stepperbackend.web.dto.PostDto;
import com.example.stepperbackend.converter.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final WeeklyMissionRepository weeklyMissionRepository;
    private final BadgeService badgeService;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;
    private final CommentRepository commentRepository;

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

        // 첫 커뮤니티 게시글 작성 완료
        if(postRepository.getCountByMember(member) == 1){
            badgeService.putFirstBadge("첫 게시글 작성 완료", member);
        }

        return PostConverter.toDto(post);
    }

    @Override
    public List<PostDto.PostViewDto> getPostsList(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        List<PostDto.PostViewDto> postList = postRepository.findAllByMember(member).stream()
                .map(post -> {
                    int likes = likeRepository.getCountByPost(post);
                    int scraps = scrapRepository.getCountByPost(post);
                    int comments = commentRepository.getCountByPost(post);
                    return PostConverter.toViewDto(post, scraps, likes, comments);
                })
                .collect(Collectors.toList());

        if (postList.isEmpty()) {
            throw new PostHandler(ErrorStatus.MY_POST_LIST_NOT_FOUND);
        }

        return postList;
    }

    @Override
    public PostDto.PostViewDto getPost(Long postId, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        int likes = likeRepository.getCountByPost(post);

        int scraps = scrapRepository.getCountByPost(post);

        int commentsCount= commentRepository.getCountByPost(post);

        return PostConverter.toViewDto(post,likes,scraps,commentsCount);
    }

    @Override
    public List<PostDto.PostViewDto> getAllPost(String categoryName, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        List<PostDto.PostViewDto> postList = postRepository.findByCategoryId(BodyPart.valueOf(categoryName)).stream()
                .map(post -> {
                    int likes = likeRepository.getCountByPost(post);
                    int scraps = scrapRepository.getCountByPost(post);
                    int comments = commentRepository.getCountByPost(post);
                    return PostConverter.toViewDto(post, scraps, likes, comments);
                })
                .collect(Collectors.toList());

        if (postList.isEmpty()) {
            throw new PostHandler(ErrorStatus.MY_POST_LIST_NOT_FOUND);
        }

        return postList;
    }
}

