package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {
    private Long id;
    private String name;
    private Boolean isActive;
}
