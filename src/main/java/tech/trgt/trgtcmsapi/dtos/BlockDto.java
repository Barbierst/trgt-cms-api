package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlockDto {

    @NotBlank(message = "Title is required")
    private String title;
    private String uuid;
    private String content;
    private String buttonText;
    private String buttonLink;

    private ImageDto imageDto;
}
