package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MenuDto {

    private String uuid;

    @NotBlank(message = "Title is required")
    private String title;

    private List<MenuItemDto> menuItems;
}
