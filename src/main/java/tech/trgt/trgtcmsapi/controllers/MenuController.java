package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.trgt.trgtcmsapi.dtos.MenuListDto;
import tech.trgt.trgtcmsapi.services.MenuService;

@RestController
@RequestMapping(MenuController.BASE_URL)
public class MenuController {
    public static final String BASE_URL = "/api/v1/menus";

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MenuListDto getAllMenus() {
        return new MenuListDto(menuService.getAllMenus());
    }
}
