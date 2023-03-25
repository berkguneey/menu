package com.qr.menu.dto.response;

import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuProductResponse {
    private BaseResponse baseResponse = new BaseResponse();
    private List<MenuProductDto> menuProducts;
}
