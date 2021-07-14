package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

@Data
public class BlogDto {
    private String slug;
    private String content;
    private String title;
    private String uuid;

    private SeoDto seo;
    private ImageDto image;
}
