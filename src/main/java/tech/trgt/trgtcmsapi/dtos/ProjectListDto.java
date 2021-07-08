package tech.trgt.trgtcmsapi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProjectListDto {

    private List<ProjectDto> projects;
}
