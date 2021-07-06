package tech.trgt.trgtcmsapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExperienceListDto {
    List<ExperienceDto> experiences;
}
