package com.qr.menu.repository;

import com.qr.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByRestaurantId(Long restaurantId);

    Optional<Menu> findByRestaurantIdAndIsActive(Long restaurantId, Boolean isActive);

}
