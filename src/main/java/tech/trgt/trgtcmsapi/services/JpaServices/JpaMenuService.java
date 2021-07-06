package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.trgt.trgtcmsapi.dtos.MenuDto;
import tech.trgt.trgtcmsapi.dtos.MenuItemDto;
import tech.trgt.trgtcmsapi.mappers.MenuMapper;
import tech.trgt.trgtcmsapi.models.Menu;
import tech.trgt.trgtcmsapi.models.MenuItem;
import tech.trgt.trgtcmsapi.repositories.MenuItemRepository;
import tech.trgt.trgtcmsapi.repositories.MenuRepository;
import tech.trgt.trgtcmsapi.services.MenuService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaMenuService implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public JpaMenuService(MenuMapper menuMapper, MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream().map(menuMapper::menuToMenuDto).collect(Collectors.toList());
    }

    @Override
    public MenuDto getMenuByUuid(String uuid) {
        Menu menu = menuRepository.findByUuid(uuid);

        if (menu == null) {
            throw new ResourceNotFoundException();
        }

        return menuMapper.menuToMenuDto(menu);
    }

    @Override
    public MenuDto createNewMenu(MenuDto menuDto) {
        Menu menu = menuMapper.menuDtoToMenu(menuDto);
        menu.setUuid(UUID.randomUUID().toString());

        return saveAndReturnMenuDto(menu);
    }

    @Override
    public MenuDto updateMenu(String uuid, MenuDto menuDto) {
        Menu original = menuRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Menu menu = menuMapper.menuDtoToMenu(menuDto);
        menu.setId(original.getId());
        menu.setUuid(original.getUuid());

        return saveAndReturnMenuDto(menu);
    }

    @Override
    public MenuDto patchMenu(String uuid, MenuDto menuDto) {
        Menu menu = menuRepository.findByUuid(uuid);

        if (menu == null) {
            throw new ResourceNotFoundException();
        }

        if (menuDto.getTitle() != null) {
            menu.setTitle(menuDto.getTitle());
        }

        return saveAndReturnMenuDto(menu);
    }

    @Override
    @Transactional
    public void deleteMenuByUuid(String uuid) {
        menuRepository.deleteByUuid(uuid);
    }

    @Override
    public MenuItemDto createNewMenuItem(String menuUuid, MenuItemDto menuItemDto) {
        Menu menu = menuRepository.findByUuid(menuUuid);

        if (menu == null) {
            throw new ResourceNotFoundException();
        }

        MenuItem menuItem = menuMapper.menuItemDtoToMenuItem(menuItemDto);
        menuItem.setUuid(UUID.randomUUID().toString());
        menuItem.setMenu(menu);

        return saveAndReturnMenuItemDto(menuItem);
    }

    @Override
    public MenuItemDto updateMenuItem(String uuid, MenuItemDto menuItemDto) {
        MenuItem original = menuItemRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        MenuItem menuItem = menuMapper.menuItemDtoToMenuItem(menuItemDto);
        menuItem.setUuid(original.getUuid());
        menuItem.setId(original.getId());

        return saveAndReturnMenuItemDto(menuItem);
    }

    @Override
    public MenuItemDto patchMenuItem(String uuid, MenuItemDto menuItemDto) {
        MenuItem menuItem = menuItemRepository.findByUuid(uuid);

        if (menuItem == null) {
            throw new ResourceNotFoundException();
        }

        if (menuItemDto.getSlug() != null) {
            menuItem.setSlug(menuItemDto.getSlug());
        }

        if (menuItemDto.getTitle() != null) {
            menuItem.setTitle(menuItemDto.getTitle());
        }

        if (menuItemDto.getIsExternal() != null) {
            menuItem.setIsExternal(menuItemDto.getIsExternal());
        }

        return saveAndReturnMenuItemDto(menuItem);
    }

    @Override
    @Transactional
    public void deleteMenuItemByUuid(String uuid) {
        menuItemRepository.deleteByUuid(uuid);
    }

    private MenuDto saveAndReturnMenuDto(Menu menu) {
        Menu savedMenu = menuRepository.save(menu);

        return menuMapper.menuToMenuDto(savedMenu);
    }

    private MenuItemDto saveAndReturnMenuItemDto(MenuItem menuItem) {
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return menuMapper.menuItemToMenuItemDto(savedMenuItem);
    }
}
