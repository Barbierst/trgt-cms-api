package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.ExperienceDto;
import tech.trgt.trgtcmsapi.dtos.ExperienceListDto;
import tech.trgt.trgtcmsapi.services.ExperienceService;

import javax.validation.Valid;

@RestController
@RequestMapping(ExperienceController.BASE_URL)
public class ExperienceController {
    public static final String BASE_URL = "/api/v1/experiences";

    private final ExperienceService ExperienceService;

    public ExperienceController(ExperienceService ExperienceService) {
        this.ExperienceService = ExperienceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ExperienceListDto getAllExperiences() {
        return new ExperienceListDto(ExperienceService.getAllExperiences());
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ExperienceDto getExperienceUuid(@PathVariable String uuid) {
        return ExperienceService.getExperienceByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ExperienceDto createNewExperience(@Valid @RequestBody ExperienceDto ExperienceDto) {
        return ExperienceService.createNewExperience(ExperienceDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ExperienceDto updateExperience(@Valid @PathVariable String uuid, @RequestBody ExperienceDto ExperienceDto) {
        return ExperienceService.updateExperience(uuid, ExperienceDto);
    }

    @PatchMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ExperienceDto patchExperience(@PathVariable String uuid, @RequestBody ExperienceDto ExperienceDto) {
        return ExperienceService.patchExperience(uuid, ExperienceDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExperience(@PathVariable String uuid) {
        ExperienceService.deleteExperienceByUuid(uuid);
    }
}
