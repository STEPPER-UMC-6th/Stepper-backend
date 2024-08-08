package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class CommentHandler extends GeneralException {
    public CommentHandler(BaseErrorCode code) {
        super(code);
    }
}
