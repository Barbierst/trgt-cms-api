package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
