package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class MyExerciseHandler extends GeneralException {
    public MyExerciseHandler(BaseErrorCode code) {
        super(code);
    }
}
