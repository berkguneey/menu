package com.qr.menu.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.qr.menu.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private Long code;
    private String message;

    public ExceptionResponse(Exception ex, HttpStatus httpStatus) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = ex.getMessage();
    }

    public ExceptionResponse(BusinessException ex) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = ex.getHttpStatus();
        this.code = ex.getErrorCode();
        this.message = ex.getMessage();
    }

}
