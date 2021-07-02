package tech.trgt.trgtcmsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trgt.trgtcmsapi.models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
