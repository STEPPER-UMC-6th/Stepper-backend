package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}

