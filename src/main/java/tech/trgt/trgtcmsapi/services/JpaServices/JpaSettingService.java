package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.mappers.SettingMapper;
import tech.trgt.trgtcmsapi.dtos.SettingDto;
import tech.trgt.trgtcmsapi.models.Setting;
import tech.trgt.trgtcmsapi.repositories.SettingRepository;
import tech.trgt.trgtcmsapi.services.SettingService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaSettingService implements SettingService {

    private final SettingMapper settingMapper;
    private final SettingRepository settingRepository;

    public JpaSettingService(SettingMapper settingMapper, SettingRepository settingRepository) {
        this.settingMapper = settingMapper;
        this.settingRepository = settingRepository;
    }

    @Override
    public List<SettingDto> getAllSettings() {
        return settingRepository.findAll().stream().map(settingMapper::settingToSettingDto).collect(Collectors.toList());
    }

    @Override
    public SettingDto getSettingByName(String name) {
        return settingMapper.settingToSettingDto(settingRepository.findByName(name));
    }

    @Override
    public SettingDto createNewSetting(SettingDto settingDto) {
        settingDto.setUuid(UUID.randomUUID().toString());
        Setting setting = settingMapper.settingDtoToSetting(settingDto);
        Setting savedSetting = settingRepository.save(setting);

        return settingMapper.settingToSettingDto(savedSetting);
    }
}
