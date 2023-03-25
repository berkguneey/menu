package com.qr.menu.dto;

import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private BaseResponse baseResponse = new BaseResponse();
    private Long id;
    private String name;
    private String imagePath;
}
