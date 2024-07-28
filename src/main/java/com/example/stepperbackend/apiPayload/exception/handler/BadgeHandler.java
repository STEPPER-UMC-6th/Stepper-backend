package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class BadgeHandler extends GeneralException {
    public BadgeHandler(BaseErrorCode code) {
        super(code);
    }
}
