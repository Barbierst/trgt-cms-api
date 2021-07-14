package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ImageDto {
    private String uuid;

    @NotBlank(message = "Title is required")
    private String title;
    private String altText;

    @NotBlank(message = "Url is required")
    private String url;
}
