package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.MenuDto;
import tech.trgt.trgtcmsapi.dtos.MenuItemDto;
import tech.trgt.trgtcmsapi.dtos.MenuListDto;
import tech.trgt.trgtcmsapi.services.MenuService;

import javax.validation.Valid;

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

    @GetMapping({"{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public MenuDto getMenuByUuid(@PathVariable String uuid) {
        return menuService.getMenuByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MenuDto createNewMenu(@Valid @RequestBody MenuDto menuDto) {
        return menuService.createNewMenu(menuDto);
    }

    @PutMapping({"/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public MenuDto updateMenu(@PathVariable String uuid,@Valid @RequestBody MenuDto menuDto) {
        return menuService.updateMenu(uuid, menuDto);
    }

    @PatchMapping({"/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public MenuDto patchMenu(@PathVariable String uuid, @RequestBody MenuDto menuDto) {
        return menuService.patchMenu(uuid, menuDto);
    }

    @DeleteMapping({"/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenu(@PathVariable String uuid) {
        menuService.deleteMenuByUuid(uuid);
    }

    @PostMapping({"/{uuid}/menu-items"})
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDto createNewMenuItem(@PathVariable String uuid, @Valid @RequestBody MenuItemDto menuItemDto) {
        return menuService.createNewMenuItem(uuid, menuItemDto);
    }

    @PutMapping({"/{menuUuid}/menu-items/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDto updateMenuItem(@PathVariable String uuid, @Valid @RequestBody MenuItemDto menuItemDto, @PathVariable String menuUuid) {
        return menuService.updateMenuItem(uuid, menuItemDto);
    }

    @PatchMapping({"/{menuUuid}/menu-items/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDto patchMenuItem(@PathVariable String uuid, @RequestBody MenuItemDto menuItemDto, @PathVariable String menuUuid) {
        return menuService.patchMenuItem(uuid, menuItemDto);
    }

    @DeleteMapping({"/{menuUuid}/menu-items/{uuid}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenuItem(@PathVariable String uuid, @PathVariable String menuUuid) {
        menuService.deleteMenuItemByUuid(uuid);
    }



}
