package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

@Data
public class SettingDto {

    private Long id;
    private String uuid;
    private String name;
    private String value;
}
