package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MenuDto {

    private String uuid;
    private String title;

    private List<MenuItemDto> menuItems;
}
