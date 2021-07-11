package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.ProjectDto;
import tech.trgt.trgtcmsapi.mappers.ProjectMapper;
import tech.trgt.trgtcmsapi.models.Image;
import tech.trgt.trgtcmsapi.models.Project;
import tech.trgt.trgtcmsapi.models.Seo;
import tech.trgt.trgtcmsapi.repositories.ImageRepository;
import tech.trgt.trgtcmsapi.repositories.ProjectRepository;
import tech.trgt.trgtcmsapi.services.ProjectService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;
import tech.trgt.trgtcmsapi.services.SeoService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaProjectService implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final SeoService seoService;
    private final ImageRepository imageRepository;

    public JpaProjectService(ProjectMapper projectMapper, ProjectRepository projectRepository, SeoService seoService, ImageRepository imageRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.seoService = seoService;
        this.imageRepository = imageRepository;
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
            Seo seo = seoService.createSeo(projectDto.getSeo());
            project.setSeo(seo);
        }

        if (projectDto.getImage() != null) {
            Image image = imageRepository.findByUuid(projectDto.getImage().getUuid());
            project.setImage(image);
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

        setRelations(project, projectDto);

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

        setRelations(project, projectDto);

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

    private void setRelations(Project project, ProjectDto projectDto) {
        if (projectDto.getSeo() != null) {
            Seo seo = createOrPatchSeo(projectDto, project);
            project.setSeo(seo);
        }

        if (projectDto.getImage() != null) {
            Image image = imageRepository.findByUuid(projectDto.getImage().getUuid());
            project.setImage(image);
        }
    }

    private Seo createOrPatchSeo(ProjectDto projectDto, Project project) {
        Seo originalSeo = project.getSeo();

        Seo seo;
        if (originalSeo == null) {
            seo = seoService.createSeo(projectDto.getSeo());
        } else {
            seo = seoService.patchSeo(originalSeo, projectDto.getSeo());
        }

        return seo;
    }
}
