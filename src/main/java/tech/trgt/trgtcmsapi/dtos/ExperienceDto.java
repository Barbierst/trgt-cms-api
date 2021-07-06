package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ExperienceDto {

    private String uuid;

    @NotBlank(message = "Title is required")
    private String title;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Description is required")
    private String description;
}
