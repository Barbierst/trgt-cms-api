package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.SettingDto;
import tech.trgt.trgtcmsapi.models.Setting;

@Mapper
public interface SettingMapper {

    SettingMapper INSTANCE = Mappers.getMapper(SettingMapper.class);

    SettingDto settingToSettingDto(Setting setting);

    Setting settingDtoToSetting(SettingDto settingDto);
}
