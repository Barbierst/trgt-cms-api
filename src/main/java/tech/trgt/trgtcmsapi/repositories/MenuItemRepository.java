package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
