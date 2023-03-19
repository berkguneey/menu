package com.qr.menu.service.impl;

import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.entity.Menu;
import com.qr.menu.entity.Restaurant;
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
            throw new RuntimeException("Not Found!");
        }
        return menuOpt.get();
    }

    @Override
    public MenuDto addMenu(Restaurant restaurant, AddMenuRequest request) {
        // TODO name ve count kontrolü eklenmeli.
        Menu menu = menuMapper.toMenu(request);
        menu.setRestaurant(restaurant);
        return menuMapper.toMenuDto(menuRepository.save(menu));
    }

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus = menuRepository.findAll();
        if (menus.isEmpty())
            throw new RuntimeException("Not Found!");
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
    public List<MenuProductDto> findProductsAndPricesByRestaurantAndMenuId(Restaurant restaurant, Long menuId) {
        return menuProductMapper.toMenuProductDtos(menuProductRepository.findByMenuId(menuId));
    }

    @Override
    public List<MenuProductDto> findActiveMenuByRestaurant(Restaurant restaurant) {
        Menu activeMenu = menuRepository.findByRestaurantIdAndIsActive(restaurant.getId(), true).get();
        return findProductsAndPricesByRestaurantAndMenuId(restaurant, activeMenu.getId());
    }

}
