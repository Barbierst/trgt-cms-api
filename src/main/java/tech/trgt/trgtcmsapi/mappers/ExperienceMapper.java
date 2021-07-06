package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.ExperienceDto;
import tech.trgt.trgtcmsapi.models.Experience;

@Mapper
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    ExperienceDto experienceToExperienceDto(Experience experience);

    Experience experienceDtoToExperience(ExperienceDto experienceDto);
}
