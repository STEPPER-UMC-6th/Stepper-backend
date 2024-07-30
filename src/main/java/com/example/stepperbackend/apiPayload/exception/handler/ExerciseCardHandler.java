package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class ExerciseCardHandler extends GeneralException {
    public ExerciseCardHandler(BaseErrorCode code) {
        super(code);
    }
}
