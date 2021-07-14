package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PageDto {
    private String uuid;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Slug is required")
    private String slug;

    private List<BlockDto> blocks;
    private ImageDto image;
    private SeoDto seo;
}
