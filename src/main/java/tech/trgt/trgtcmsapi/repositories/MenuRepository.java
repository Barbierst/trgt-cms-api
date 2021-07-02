package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
