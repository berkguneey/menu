package com.qr.menu.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class MenuProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    //@Column(nullable = false, columnDefinition = "BINARY(16)")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

}
