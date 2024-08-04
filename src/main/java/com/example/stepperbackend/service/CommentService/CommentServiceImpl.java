package com.example.stepperbackend.service.CommentService;

import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
import com.example.stepperbackend.apiPayload.exception.handler.PostHandler;
import com.example.stepperbackend.converter.CommentConverter;
import com.example.stepperbackend.domain.Comment;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.Post;
import com.example.stepperbackend.repository.CommentRepository;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.PostRepository;
import com.example.stepperbackend.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    final MemberRepository memberRepository;
    final PostRepository postRepository;
    final CommentRepository commentRepository;


    @Override
    public CommentDto.CommentResponseDto writeComment(CommentDto.CommentRequestDto request, String Email) {
        Member member = memberRepository.findByEmail(Email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        Comment comment = CommentConverter.toEntity(request, member, post);
        commentRepository.save(comment);

        return CommentConverter.toDto(comment);




    }
}
