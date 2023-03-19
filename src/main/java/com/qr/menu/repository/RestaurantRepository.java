package com.qr.menu.repository;

import com.qr.menu.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where r.user.username = :username")
    List<Restaurant> findAllByUsername(String username);

    @Query("select r from Restaurant r where r.id = :id and r.user.username = :username")
    Optional<Restaurant> findByIdAndUsername(Long id, String username);

}
