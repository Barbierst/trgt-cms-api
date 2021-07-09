package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.ProjectDto;
import tech.trgt.trgtcmsapi.dtos.SeoDto;
import tech.trgt.trgtcmsapi.models.Project;
import tech.trgt.trgtcmsapi.models.Seo;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto projectToProjectDto(Project project);
    Project projectDtoToProject(ProjectDto projectDto);

    SeoDto seoDtoToSeo(Seo seo);
    Seo seoDtoToSeo(SeoDto seoDto);
}
