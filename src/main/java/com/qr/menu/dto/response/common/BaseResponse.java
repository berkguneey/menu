package com.qr.menu.dto.response.common;

import com.qr.menu.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private Boolean success = true;
    private Long code;
    private String message;

    public BaseResponse(Exception ex) {
        this.success = false;
        this.message = ex.getMessage();
    }

    public BaseResponse(BusinessException ex) {
        this.success = false;
        this.code = ex.getErrorCode();
        this.message = ex.getMessage();
    }

}
