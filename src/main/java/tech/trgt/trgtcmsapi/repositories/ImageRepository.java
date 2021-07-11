package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Image;
import tech.trgt.trgtcmsapi.models.Menu;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
