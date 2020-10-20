package io.starskyoio.esblog.repository;

import io.starskyoio.esblog.entity.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value="select * from t_blog o where o.title like %?1% or o.content like %?1% order by o.update_time desc", nativeQuery = true)
    List<Blog> findByKeyword(String keyword, Pageable pageable);
}