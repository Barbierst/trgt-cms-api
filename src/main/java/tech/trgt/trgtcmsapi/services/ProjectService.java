package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();

    ProjectDto getProjectByUuid(String uuid);

    ProjectDto createNewProject(ProjectDto projectDto);

    ProjectDto updateProject(String uuid, ProjectDto projectDto);

    ProjectDto patchProject(String uuid, ProjectDto projectDto);

    void deleteProjectByUuid(String uuid);
}
