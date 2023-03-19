package com.qr.menu.service.impl;

import com.qr.menu.dto.AppUserDto;
import com.qr.menu.entity.AppUser;
import com.qr.menu.repository.AppUserRepository;
import com.qr.menu.service.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService, UserDetailsService {

    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getUserRole().getName()).build();
    }

    @Override
    public AppUserDto register(AppUserDto appUserDto) {
        return null;
    }

}
