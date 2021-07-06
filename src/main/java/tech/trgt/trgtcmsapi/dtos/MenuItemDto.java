package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

@Data
public class MenuItemDto {

    private String uuid;
    private String title;
    private String slug;
    private Boolean isExternal;
}
