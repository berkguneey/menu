package com.qr.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRestaurantRequest {
    private String name;
    private String description;
    private String city;
    private String district;
    private String address;
    private String phoneNumber;
    private String email;
    private String imagePath;
    private Long userId;
}
