package com.example.stepperbackend.apiPayload.exception.handler;

import com.example.stepperbackend.apiPayload.code.BaseErrorCode;
import com.example.stepperbackend.apiPayload.exception.GeneralException;

public class RateDiaryHandler extends GeneralException {

        public RateDiaryHandler(BaseErrorCode code) {
            super(code);
        }

}
