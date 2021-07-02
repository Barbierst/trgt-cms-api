package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Seo;

public interface SeoRepository extends JpaRepository<Seo, Long> {
}
