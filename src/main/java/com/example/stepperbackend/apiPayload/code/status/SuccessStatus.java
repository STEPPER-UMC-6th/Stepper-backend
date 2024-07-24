package com.example.stepperbackend.apiPayload.code.status;

import com.example.stepperbackend.apiPayload.code.BaseCode;
import com.example.stepperbackend.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK(HttpStatus.OK, "2000", "OK");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return new ReasonDTO(httpStatus, code, message);
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return new ReasonDTO(httpStatus, code, message);
    }
}
