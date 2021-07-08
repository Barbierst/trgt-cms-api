package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.ProjectDto;
import tech.trgt.trgtcmsapi.dtos.ProjectListDto;
import tech.trgt.trgtcmsapi.services.ProjectService;

import javax.validation.Valid;

@RestController
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController {
    public static final String BASE_URL = "/api/v1/projects";

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProjectListDto getAllProjects() {
        return new ProjectListDto(projectService.getAllProjects());
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto getProjectUuid(@PathVariable String uuid) {
        return projectService.getProjectByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto createNewProject(@Valid @RequestBody ProjectDto projectDto) {
        return projectService.createNewProject(projectDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto updateProject(@Valid @PathVariable String uuid, @RequestBody ProjectDto projectDto) {
        return projectService.updateProject(uuid, projectDto);
    }

    @PatchMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto patchProject(@PathVariable String uuid, @RequestBody ProjectDto projectDto) {
        return projectService.patchProject(uuid, projectDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable String uuid) {
        projectService.deleteProjectByUuid(uuid);
    }
}
