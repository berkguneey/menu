package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMenuRequestDto {
    private String name;
    private Boolean isActive;
}
