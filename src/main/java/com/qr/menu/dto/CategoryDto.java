package com.qr.menu.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryDto {
    private Long id;
    private String name;
    private String imagePath;
}
