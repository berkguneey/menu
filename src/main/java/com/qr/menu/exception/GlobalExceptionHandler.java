package com.qr.menu.exception;

import com.qr.menu.dto.response.common.BaseResponse;
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
        BaseResponse baseResponse = new BaseResponse(ex);
        log.error("Exception Stacktrace: {}", Arrays.stream(ex.getStackTrace()).limit(depth).map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
        return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        long depth = 5L;
        BaseResponse baseResponse = new BaseResponse(ex);
        log.error("Exception Stacktrace: {}", Arrays.stream(ex.getStackTrace()).limit(depth).map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
        return new ResponseEntity<>(baseResponse, ex.getHttpStatus());
    }

}
