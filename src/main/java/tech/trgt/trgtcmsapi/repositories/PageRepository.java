package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
}
