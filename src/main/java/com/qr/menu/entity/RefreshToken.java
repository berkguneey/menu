package com.qr.menu.entity;


import com.qr.menu.entity.common.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE refresh_token SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    //@Column(nullable = false, columnDefinition = "BINARY(16)")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "userId")
    private CustomUserDetails user;

    @NotNull
    @Column(nullable = false, unique = true)
    private String token;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime expiryDate;

}
