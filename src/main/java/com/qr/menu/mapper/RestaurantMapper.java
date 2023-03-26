package com.qr.menu.mapper;

import com.qr.menu.dto.AddRestaurantDto;
import com.qr.menu.dto.RestaurantDto;
import com.qr.menu.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RestaurantMapper {

    RestaurantDto toRestaurantDto(Restaurant restaurant);

    List<RestaurantDto> toRestaurantDtos(List<Restaurant> restaurants);

    Restaurant toRestaurant(RestaurantDto restaurantDto);

    @Mapping(source = "userId", target = "user.id")
    Restaurant toRestaurant(AddRestaurantDto addRestaurantDto);

}
