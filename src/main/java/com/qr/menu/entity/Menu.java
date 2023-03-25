package com.qr.menu.entity;

import com.qr.menu.entity.common.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    //@Column(nullable = false, columnDefinition = "BINARY(16)")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private Set<MenuProduct> productPrices = new HashSet<>();

}
