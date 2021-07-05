package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.SettingDto;

import java.util.List;

public interface SettingService {
    List<SettingDto> getAllSettings();

    SettingDto getSettingByUuid(String uuid);

    SettingDto createNewSetting(SettingDto settingDto);

    SettingDto updateSetting(String uuid, SettingDto settingDto);

    SettingDto patchSetting(String uuid, SettingDto settingDto);

    void deleteSettingByUuid(String uuid);
}
