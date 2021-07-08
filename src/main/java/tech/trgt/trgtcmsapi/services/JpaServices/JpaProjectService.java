package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.ProjectDto;
import tech.trgt.trgtcmsapi.mappers.ProjectMapper;
import tech.trgt.trgtcmsapi.models.Project;
import tech.trgt.trgtcmsapi.repositories.ProjectRepository;
import tech.trgt.trgtcmsapi.repositories.SeoRepository;
import tech.trgt.trgtcmsapi.services.ProjectService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaProjectService implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final SeoRepository seoRepository;


    public JpaProjectService(ProjectMapper projectMapper, ProjectRepository projectRepository, SeoRepository seoRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.seoRepository = seoRepository;
    }

    @Override
    public List<ProjectDto> getAllProject() {
        return projectRepository.findAll().stream().map(projectMapper::projectToProjectDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectByUuid(String uuid) {
        Project project = projectRepository.findByUuid(uuid);

        if (project == null) {
            throw new ResourceNotFoundException();
        }

        return projectMapper.projectToProjectDto(project);
    }

    @Override
    public ProjectDto createNewProject(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto updateProject(String uuid, ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto patchProject(String uuid, ProjectDto projectDto) {
        return null;
    }

    @Override
    public void deleteProjectByUuid(String uuid) {

    }
}
