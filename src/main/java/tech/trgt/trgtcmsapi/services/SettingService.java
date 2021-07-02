package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.SettingDto;

import java.util.List;

public interface SettingService {
    List<SettingDto> getAllSettings();

    SettingDto getSettingByName(String name);

    SettingDto createNewSetting(SettingDto settingDto);
}
