package com.qr.menu.dto.response;

import com.qr.menu.dto.MenuProductCategoryBasedDto;
import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuProductCategoryBasedResponse {
    private BaseResponse baseResponse = new BaseResponse();
    private List<MenuProductCategoryBasedDto> menuProducts;
}
