package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Block;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Block findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
