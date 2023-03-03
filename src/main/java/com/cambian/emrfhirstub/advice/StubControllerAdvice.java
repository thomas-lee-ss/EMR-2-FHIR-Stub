package com.cambian.emrfhirstub.advice;

import com.cambian.emrfhirstub.exception.StubException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StubControllerAdvice {

    @ExceptionHandler({ StubException.class })
    public ResponseEntity handleException(StubException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

}
