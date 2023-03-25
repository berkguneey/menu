package com.qr.menu.dto;

import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
    private BaseResponse baseResponse = new BaseResponse();
    private Long id;
    private String name;
    private String description;
    private String city;
    private String district;
    private String address;
    private String phoneNumber;
    private String email;
    private String imagePath;
    private byte[] qrCode;
}
