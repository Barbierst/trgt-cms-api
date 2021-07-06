package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.MenuDto;
import tech.trgt.trgtcmsapi.mappers.MenuMapper;
import tech.trgt.trgtcmsapi.repositories.MenuRepository;
import tech.trgt.trgtcmsapi.services.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaMenuService implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    public JpaMenuService(MenuMapper menuMapper, MenuRepository menuRepository) {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream().map(menuMapper::menuToMenuDto).collect(Collectors.toList());
    }

    @Override
    public MenuDto getMenuByUuid(String uuid) {
        return null;
    }

    @Override
    public MenuDto createNewMenu(MenuDto MenuDto) {
        return null;
    }

    @Override
    public MenuDto updateMenu(String uuid, MenuDto MenuDto) {
        return null;
    }

    @Override
    public MenuDto patchMenu(String uuid, MenuDto MenuDto) {
        return null;
    }

    @Override
    public void deleteMenuByUuid(String uuid) {

    }
}
