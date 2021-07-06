package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.MenuDto;
import tech.trgt.trgtcmsapi.dtos.MenuItemDto;
import tech.trgt.trgtcmsapi.models.Menu;
import tech.trgt.trgtcmsapi.models.MenuItem;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    Menu menuDtoToMenu(MenuDto menuDto);

    MenuDto menuToMenuDto(Menu menu);

    @Mapping(target = "menu")
    List<MenuItemDto> menuItemsToMenuItemDtos(List<MenuItem> menuItems);

    @Mapping(target = "menu")
    List<MenuItem> menuItemDtosToMenuItems(List<MenuItemDto> menuItemDtos);
}
