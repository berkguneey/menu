package com.qr.menu.exception;

import com.qr.menu.dto.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        long depth = 5L;
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("Exception Stacktrace: {}", Arrays.stream(ex.getStackTrace()).limit(depth).map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getHttpStatus());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        long depth = 5L;
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
        log.error("Exception Stacktrace: {}", Arrays.stream(ex.getStackTrace()).limit(depth).map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getHttpStatus());
    }

}
