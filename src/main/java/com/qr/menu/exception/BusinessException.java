package com.qr.menu.exception;

import com.qr.menu.constant.ErrorConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Long errorCode;
    private final String message;

    public BusinessException(Long errorCode, String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ErrorConstants errorConstants) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = errorConstants.getCode();
        this.message = errorConstants.getMessage();
    }

    public BusinessException(ErrorConstants errorConstants, Object... args) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = errorConstants.getCode();
        this.message = String.format(errorConstants.getMessage(), args);
    }

    public BusinessException(HttpStatus httpStatus, ErrorConstants errorConstants) {
        this.httpStatus = httpStatus;
        this.errorCode = errorConstants.getCode();
        this.message = errorConstants.getMessage();
    }

    public BusinessException(HttpStatus httpStatus, ErrorConstants errorConstants, Object... args) {
        this.httpStatus = httpStatus;
        this.errorCode = errorConstants.getCode();
        this.message = String.format(errorConstants.getMessage(), args);
    }

}
