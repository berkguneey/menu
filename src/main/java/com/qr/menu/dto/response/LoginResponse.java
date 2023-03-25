package com.qr.menu.dto.response;

import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private BaseResponse baseResponse = new BaseResponse();
    private String username;
    private String jwtType = "Bearer ";
    private String jwtToken;
}
