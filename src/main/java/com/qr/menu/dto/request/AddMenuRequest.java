package com.qr.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMenuRequest {
    private String name;
    private Boolean isActive;
}
