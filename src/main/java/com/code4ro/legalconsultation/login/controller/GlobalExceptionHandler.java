package com.code4ro.legalconsultation.login.controller;

import com.code4ro.legalconsultation.login.exception.ValidationException;
import com.code4ro.legalconsultation.login.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<ApiResponse> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity(new ApiResponse(false, ex.getMessage()),
                HttpStatus.CONFLICT);
    }
}
