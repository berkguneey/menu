package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.AddMenuDto;
import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.entity.Menu;
import com.qr.menu.entity.Restaurant;
import com.qr.menu.exception.BusinessException;
import com.qr.menu.mapper.MenuMapper;
import com.qr.menu.mapper.MenuProductMapper;
import com.qr.menu.repository.MenuProductRepository;
import com.qr.menu.repository.MenuRepository;
import com.qr.menu.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final MenuProductRepository menuProductRepository;
    private final MenuProductMapper menuProductMapper;

    @Override
    public Menu getOne(Long id) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        if (!menuOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR104);
        }
        return menuOpt.get();
    }

    @Override
    public MenuDto addMenu(Restaurant restaurant, AddMenuDto request) {
        long menuCount = menuRepository.countMenusByRestaurantId(restaurant.getId());
        if (menuCount == 3) {
            throw new BusinessException(ErrorConstants.ERR106);
        }
        Optional<Menu> menuOpt = menuRepository.findByName(request.getName());
        if (menuOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR105);
        }
        Menu menu = menuMapper.toMenu(request);
        menu.setRestaurant(restaurant);
        return menuMapper.toMenuDto(menuRepository.save(menu));
    }

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus = menuRepository.findAll();
        if (menus.isEmpty()) {
            throw new BusinessException(ErrorConstants.ERR104);
        }
        return menuMapper.toMenuDtos(menus);
    }

    @Override
    public MenuDto findById(Long id) {
        Menu menu = getOne(id);
        return menuMapper.toMenuDto(menu);
    }

    @Override
    public List<MenuDto> findAllByRestaurant(Restaurant restaurant) {
        return menuMapper.toMenuDtos(menuRepository.findByRestaurantId(restaurant.getId()));
    }

    @Override
    public List<MenuProductDto> findProductsAndPricesByMenuId(Restaurant restaurant, Long menuId) {
        Optional<Menu> menuOpt = menuRepository.findByIdAndRestaurantId(menuId, restaurant.getId());
        if (!menuOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR111);
        }
        return menuProductMapper.toMenuProductDtos(menuProductRepository.findByMenuId(menuId));
    }

    @Override
    public List<MenuProductDto> findActiveMenuByRestaurant(Restaurant restaurant) {
        Optional<Menu> activeMenuOpt = menuRepository.findByRestaurantIdAndIsActive(restaurant.getId(), true);
        if (!activeMenuOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR112);
        }
        return findProductsAndPricesByMenuId(restaurant, activeMenuOpt.get().getId());
    }

}
