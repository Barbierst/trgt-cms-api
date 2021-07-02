package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.SettingDto;
import tech.trgt.trgtcmsapi.dtos.SettingListDto;
import tech.trgt.trgtcmsapi.services.SettingService;

@Controller
@RequestMapping("/api/v1/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public ResponseEntity<SettingListDto> getAllSettings() {
        return new ResponseEntity<SettingListDto>(new SettingListDto(settingService.getAllSettings()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<SettingDto> getSettingByName(@PathVariable String name) {
        return new ResponseEntity<SettingDto>(settingService.getSettingByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SettingDto> createNewSetting(@RequestBody SettingDto settingDto) {
        return new ResponseEntity<SettingDto>(settingService.createNewSetting(settingDto), HttpStatus.CREATED);
    }
}
