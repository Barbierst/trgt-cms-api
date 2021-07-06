package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.ExperienceDto;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDto> getAllExperiences();

    ExperienceDto getExperienceByUuid(String uuid);

    ExperienceDto createNewExperience(ExperienceDto experienceDto);

    ExperienceDto updateExperience(String uuid,ExperienceDto experienceDto);

    ExperienceDto patchExperience(String uuid, ExperienceDto experienceDto);

    void deleteExperienceByUuid(String uuid);
}
