package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class ExerciseStepHandler extends GeneralException {
    public ExerciseStepHandler(BaseErrorCode code) {
        super(code);
    }
}
