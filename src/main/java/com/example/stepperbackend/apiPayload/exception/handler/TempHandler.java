package com.example.stepperbackend.apiPayload.exception.handler;


import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
