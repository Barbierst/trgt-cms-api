package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    Setting findByName(String name);
    Setting findByUuid(String uuid);
    void deleteByUuid(String uuid);

}
