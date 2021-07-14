package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlogDto {
    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "Content is required")
    private String content;

    @NotBlank(message = "Title is required")
    private String title;
    private String uuid;

    private SeoDto seo;
    private ImageDto image;
}
