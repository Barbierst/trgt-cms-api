package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public SettingDto getSettingByUuid(String uuid) {
        return settingMapper.settingToSettingDto(settingRepository.findByUuid(uuid));
    }

    @Override
    public SettingDto createNewSetting(SettingDto settingDto) {
        Setting setting = settingMapper.settingDtoToSetting(settingDto);
        setting.setUuid(UUID.randomUUID().toString());

        return saveAndReturnDto(setting);
    }

    @Override
    public SettingDto updateSetting(String uuid, SettingDto settingDto) {
        Setting original = settingRepository.findByUuid(uuid);
        Setting setting = settingMapper.settingDtoToSetting(settingDto);
        setting.setId(original.getId());
        setting.setUuid(original.getUuid());

        return saveAndReturnDto(setting);
    }

    @Override
    public SettingDto patchSetting(String uuid, SettingDto settingDto) {
        Setting setting = settingRepository.findByUuid(uuid);

        if (settingDto.getName() != null) {
            setting.setName(settingDto.getName());
        }

        if (settingDto.getValue() != null) {
            setting.setValue(settingDto.getValue());
        }

        return saveAndReturnDto(setting);
    }

    @Override
    @Transactional
    public void deleteSettingByUuid(String uuid) {
        settingRepository.deleteByUuid(uuid);
    }

    private SettingDto saveAndReturnDto(Setting setting) {
        Setting savedSetting = settingRepository.save(setting);

        return settingMapper.settingToSettingDto(savedSetting);
    }


}
