package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.SettingDto;
import tech.trgt.trgtcmsapi.dtos.SettingListDto;
import tech.trgt.trgtcmsapi.services.SettingService;

@RestController
@RequestMapping(SettingController.BASE_URL)
public class SettingController {
    public static final String BASE_URL = "/api/v1/settings";

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SettingListDto getAllSettings() {
        return new SettingListDto(settingService.getAllSettings());
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public SettingDto getSettingUuid(@PathVariable String uuid) {
        return settingService.getSettingByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SettingDto createNewSetting(@RequestBody SettingDto settingDto) {
        return settingService.createNewSetting(settingDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public SettingDto updateSetting(@PathVariable String uuid, @RequestBody SettingDto settingDto) {
        return settingService.updateSetting(uuid, settingDto);
    }

    @PatchMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public SettingDto patchSetting(@PathVariable String uuid, @RequestBody SettingDto settingDto) {
        return settingService.patchSetting(uuid, settingDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSetting(@PathVariable String uuid) {
        settingService.deleteSettingByUuid(uuid);
    }
}
