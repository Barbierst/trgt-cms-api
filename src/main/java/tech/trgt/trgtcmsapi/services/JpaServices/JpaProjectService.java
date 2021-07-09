package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.ProjectDto;
import tech.trgt.trgtcmsapi.dtos.SeoDto;
import tech.trgt.trgtcmsapi.mappers.ProjectMapper;
import tech.trgt.trgtcmsapi.models.Menu;
import tech.trgt.trgtcmsapi.models.Project;
import tech.trgt.trgtcmsapi.models.Seo;
import tech.trgt.trgtcmsapi.repositories.ProjectRepository;
import tech.trgt.trgtcmsapi.repositories.SeoRepository;
import tech.trgt.trgtcmsapi.services.ProjectService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
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
    public List<ProjectDto> getAllProjects() {
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
        Project project = projectMapper.projectDtoToProject(projectDto);
        project.setUuid(UUID.randomUUID().toString());

        if (projectDto.getSeo() != null) {
            Seo seo = projectMapper.seoDtoToSeo(projectDto.getSeo());
            seo.setUuid(UUID.randomUUID().toString());

            project.setSeo(seo);
        }

        return saveAndReturnDto(project);
    }


    @Override
    @Transactional
    public ProjectDto updateProject(String uuid, ProjectDto projectDto) {
        Project original = projectRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Project project = projectMapper.projectDtoToProject(projectDto);

        project.setUuid(original.getUuid());
        project.setId(original.getId());

        if (projectDto.getSeo() != null) {
            Seo originalSeo = original.getSeo();
            Seo seo = projectMapper.seoDtoToSeo(projectDto.getSeo());

            seo.setUuid(originalSeo.getUuid());
            seo.setId(originalSeo.getId());

            project.setSeo(seo);
        }

        return saveAndReturnDto(project);
    }

    @Override
    @Transactional
    public ProjectDto patchProject(String uuid, ProjectDto projectDto) {
        Project project = projectRepository.findByUuid(uuid);

        if (project == null) {
            throw new ResourceNotFoundException();
        }

        if (projectDto.getContent() != null) {
            project.setContent(projectDto.getContent());
        }
        if (projectDto.getLiveLink() != null) {
            project.setLiveLink(projectDto.getLiveLink());
        }
        if (projectDto.getRepoLink() != null) {
            project.setRepoLink(projectDto.getRepoLink());
        }
        if (projectDto.getTitle() != null) {
            project.setTitle(projectDto.getTitle());
        }

        if (projectDto.getSeo() != null) {
            SeoDto seoDto = projectDto.getSeo();
            Seo seo = project.getSeo();

            if (seo == null) {
                Seo newSeo = projectMapper.seoDtoToSeo(seoDto);
                newSeo.setUuid(UUID.randomUUID().toString());

                project.setSeo(newSeo);
            } else {
                if (seoDto.getTitle() != null) {
                    seo.setTitle(seoDto.getTitle());
                }
                if (seoDto.getDescription() != null) {
                    seo.setDescription(seoDto.getDescription());
                }
            }
        }

        return saveAndReturnDto(project);
    }

    @Override
    @Transactional
    public void deleteProjectByUuid(String uuid) {
        projectRepository.deleteByUuid(uuid);
    }

    private ProjectDto saveAndReturnDto(Project project) {
        Project savedProject = projectRepository.save(project);

        return projectMapper.projectToProjectDto(savedProject);
    }
}
