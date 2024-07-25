package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class ExerciseHandler extends GeneralException {
    public ExerciseHandler(BaseErrorCode code) {
        super(code);
    }
}
