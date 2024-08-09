package com.example.stepperbackend.service.CommentService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.CommentHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
import com.example.stepperbackend.converter.CommentConverter;
import com.example.stepperbackend.converter.PostConverter;
import com.example.stepperbackend.domain.Comment;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.repository.CommentRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.PostRepository;
import com.example.stepperbackend.web.dto.CommentDto;
import com.example.stepperbackend.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    final MemberRepository memberRepository;
    final PostRepository postRepository;
    final CommentRepository commentRepository;

    private Map<Long, Integer> anonymousCountMap = new HashMap<>();

    @Override
    public CommentDto.CommentResponseDto writeComment(CommentDto.CommentRequestDto request, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        List<Comment> commentList = commentRepository.findByPost_IdAndMember_Id(post.getId(), member.getId());

        String memberName;

        if (request.isAnonymous()) {
            memberName = getAnonymousName(commentList, post.getId());
        } else {
            memberName = member.getName();
        }

        Comment comment = CommentConverter.toEntity(request, member, post, memberName);
        commentRepository.save(comment);

        return CommentConverter.toDto(comment);
    }



    @Override
    public CommentDto.CommentResponseDto writeReply(CommentDto.ReplyRequestDto request, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        Comment parentComment = commentRepository.findById(request.getParentCommentId())
                .orElseThrow(() -> new CommentHandler(ErrorStatus.PARENT_COMMENT_NOT_FOUND));

        List<Comment> commentList = commentRepository.findByPost_IdAndMember_Id(post.getId(), member.getId());

        String memberName;

        if (request.isAnonymous()) {
            memberName = getAnonymousName(commentList, post.getId());
        } else {
            memberName = member.getName();
        }

        Comment reply = CommentConverter.toReplyEntity(request, member, post, memberName, parentComment);
        commentRepository.save(reply);

        return CommentConverter.toDto(reply);
    }


    // 익명 이름 생성 메서드
    private String getAnonymousName(List<Comment> commentList, Long postId) {
        if (!commentList.isEmpty()) {
            return commentList.get(0).getAnonymousName();
        } else {
            int anonymousCount = anonymousCountMap.getOrDefault(postId, 0) + 1;
            anonymousCountMap.put(postId, anonymousCount);
            return "익명" + anonymousCount;
        }
    }

    @Override
    public List<CommentDto.CommentResponseDto> getComment(Long postId) {

        List<Comment> commentList = commentRepository.findByPostId(postId);

        if (commentList.isEmpty()) {
            throw new CommentHandler(ErrorStatus.COMMENTS_NOT_FOUND);
        }

        return commentList.stream().map(CommentConverter::toDto).collect(Collectors.toList());
    }
}

