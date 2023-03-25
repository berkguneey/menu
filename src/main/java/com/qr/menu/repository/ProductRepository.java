package com.qr.menu.repository;

import com.qr.menu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p inner join p.productPrices productPrices where p.restaurant.id = :restaurantId and p.name = :name and productPrices.menu.id = :menuId")
    Optional<Product> findByNameAndRestaurantIdAndMenuId(String name, Long restaurantId, Long menuId);

}
