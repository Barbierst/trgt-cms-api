package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
