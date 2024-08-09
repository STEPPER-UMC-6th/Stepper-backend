package com.example.stepperbackend.apiPayload.code.status;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // member
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER4003", "잘못된 비밀번호입니다."),

    // more_exercise
    MORE_EXERCISE_NOT_FOUND(HttpStatus.BAD_REQUEST, "MORE_EXERCISE4001", "추가 운동 기록을 찾을 수 없습니다."),

    // badge
    BADGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "BADGE4001", "벳지가 없습니다."),
    BADGE_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "BADGE4001", "벳지 카테고리가 없습니다."),

    // my_exercise
    MY_EXERCISE_NOT_FOUND(HttpStatus.BAD_REQUEST, "MY_EXERCISE4001", "나만의 운동을 찾을 수 없습니다."),

    // exercise_card
    EXERCISE_CARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "EXERCISE_CARD4001", "운동 카드를 찾을 수 없습니다."),

    // rate_diary
    EXERCISE_CARD_DOES_NOT_BELONG_TO_USER(HttpStatus.BAD_REQUEST, "RATE_DIARY4001", "운동 카드가 유저의 것이 아닙니다."),
    RATE_DIARY_NOT_FOUND(HttpStatus.BAD_REQUEST, "RATE_DIARY4001", "평가 일지를 찾을 수 없습니다."),

    //exercise_step
    EXERCISE_STEP_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "EXERCISE_STEP4001", "스텝 아이디를 찾을 수 없습니다."),

    // post
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST4001", "게시글을 찾을 수 없습니다."),
    MY_POST_LIST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST_LIST4001", "내 글 목록을 찾을 수 없습니다."),

    //like
    LIKE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "LIKE4001", "이미 좋아요를 눌렀습니다."),

    // scrap
    SCRAP_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "SCRAP4001", "이미 스크랩된 게시물입니다."),

    //comment
    MY_COMMENTS_NOT_FOUND(HttpStatus.BAD_REQUEST, "MY_COMMENTS4001", "내가 작성한 댓글이 없습니다."),
    COMMENTS_NOT_FOUND(HttpStatus.BAD_REQUEST, "COMMENT4001", "댓글이 없습니다."),
    PARENT_COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "COMMENT4002", "부모 댓글이 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
