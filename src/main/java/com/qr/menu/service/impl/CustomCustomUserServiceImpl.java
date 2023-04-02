package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.request.RegisterRequest;
import com.qr.menu.entity.CustomUserDetails;
import com.qr.menu.repository.CustomUserRepository;
import com.qr.menu.service.ICustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomCustomUserServiceImpl implements ICustomUserService, UserDetailsService {

    private final CustomUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails user = findByUsername(username);
        return User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRole().getName()).build();
    }

    @Override
    public RegisterRequest register(RegisterRequest request) {
        return null;
    }

    @Override
    public CustomUserDetails findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ErrorConstants.ERR103.getMessage()));
    }

}
