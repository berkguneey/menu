package com.qr.menu.dto;

import com.qr.menu.dto.response.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuProductDto {
    private BaseResponse baseResponse;
    private Long id;
    private ProductDto product;
    private BigDecimal price;
}
