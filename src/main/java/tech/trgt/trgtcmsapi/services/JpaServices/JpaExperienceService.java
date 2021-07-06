package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.trgt.trgtcmsapi.dtos.ExperienceDto;
import tech.trgt.trgtcmsapi.mappers.ExperienceMapper;
import tech.trgt.trgtcmsapi.models.Experience;
import tech.trgt.trgtcmsapi.repositories.ExperienceRepository;
import tech.trgt.trgtcmsapi.services.ExperienceService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaExperienceService implements ExperienceService {

    private final ExperienceMapper experienceMapper;
    private final ExperienceRepository experienceRepository;

    public JpaExperienceService(ExperienceMapper experienceMapper, ExperienceRepository experienceRepository) {
        this.experienceMapper = experienceMapper;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return experienceRepository.findAll().stream().map(experienceMapper::experienceToExperienceDto).collect(Collectors.toList());
    }

    @Override
    public ExperienceDto getExperienceByUuid(String uuid) {
        Experience experience = experienceRepository.findByUuid(uuid);

        if (experience == null) {
            throw new ResourceNotFoundException();
        }

        return experienceMapper.experienceToExperienceDto(experience);
    }

    @Override
    public ExperienceDto createNewExperience(ExperienceDto experienceDto) {
       Experience experience = experienceMapper.experienceDtoToExperience(experienceDto);
       experience.setUuid(UUID.randomUUID().toString());

       return saveAndReturnDto(experience);
    }

    @Override
    @Transactional
    public ExperienceDto updateExperience(String uuid, ExperienceDto experienceDto) {
        Experience original = experienceRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Experience experience = experienceMapper.experienceDtoToExperience(experienceDto);
        experience.setUuid(original.getUuid());
        experience.setId(original.getId());

        return saveAndReturnDto(experience);
    }

    @Override
    @Transactional
    public ExperienceDto patchExperience(String uuid, ExperienceDto experienceDto) {
        Experience experience = experienceRepository.findByUuid(uuid);

        if (experience == null) {
            throw new ResourceNotFoundException();
        }

        if (experienceDto.getTitle() != null) {
            experience.setTitle(experienceDto.getTitle());
        }
        if (experienceDto.getStartDate() != null) {
            experience.setStartDate(experienceDto.getStartDate());
        }
        if (experienceDto.getEndDate() != null) {
            experience.setEndDate(experienceDto.getEndDate());
        }
        if (experienceDto.getCompany() != null) {
            experience.setCompany(experienceDto.getCompany());
        }
        if (experienceDto.getDescription() != null) {
            experience.setDescription(experienceDto.getDescription());
        }

        return saveAndReturnDto(experience);
    }

    @Override
    @Transactional
    public void deleteExperienceByUuid(String uuid) {
        experienceRepository.deleteByUuid(uuid);
    }

    private ExperienceDto saveAndReturnDto(Experience experience) {
        Experience savedExperience = experienceRepository.save(experience);

        return experienceMapper.experienceToExperienceDto(savedExperience);
    }
}
