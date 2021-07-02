package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
