package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.MenuDto;

import java.util.List;

public interface MenuService {
    List<MenuDto> getAllMenus();

    MenuDto getMenuByUuid(String uuid);

    MenuDto createNewMenu(MenuDto MenuDto);

    MenuDto updateMenu(String uuid, MenuDto MenuDto);

    MenuDto patchMenu(String uuid, MenuDto MenuDto);

    void deleteMenuByUuid(String uuid);
}
