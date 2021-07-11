package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectDto {
    private String uuid;

    @NotBlank(message = "Title is required")
    private String title;
    private String content;
    private String liveLink;
    private String repoLink;

    private SeoDto seo;

    private ImageDto image;
}
