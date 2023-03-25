package com.qr.menu.dto.response;

import com.qr.menu.dto.ProductDto;
import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private BaseResponse baseResponse = new BaseResponse();
    private List<ProductDto> products;
}
