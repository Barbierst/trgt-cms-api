package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.SettingDto;
import tech.trgt.trgtcmsapi.dtos.SettingListDto;
import tech.trgt.trgtcmsapi.services.SettingService;

@Controller
@RequestMapping(SettingController.BASE_URL)
public class SettingController {
    public static final String BASE_URL = "/api/v1/settings";

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public ResponseEntity<SettingListDto> getAllSettings() {
        return new ResponseEntity<SettingListDto>(new SettingListDto(settingService.getAllSettings()), HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<SettingDto> getSettingUuid(@PathVariable String uuid) {
        return new ResponseEntity<SettingDto>(settingService.getSettingByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SettingDto> createNewSetting(@RequestBody SettingDto settingDto) {
        return new ResponseEntity<SettingDto>(settingService.createNewSetting(settingDto), HttpStatus.CREATED);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<SettingDto> updateSetting(@PathVariable String uuid, @RequestBody SettingDto settingDto) {
        return new ResponseEntity<SettingDto>(settingService.updateSetting(uuid, settingDto), HttpStatus.OK);
    }

    @PatchMapping("{uuid}")
    public ResponseEntity<SettingDto> patchSetting(@PathVariable String uuid, @RequestBody SettingDto settingDto) {
        return new ResponseEntity<SettingDto>(settingService.patchSetting(uuid, settingDto), HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deleteSetting(@PathVariable String uuid) {
        settingService.deleteSettingByUuid(uuid);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
