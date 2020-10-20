package io.starskyoio.esblog.repository;

import io.starskyoio.esblog.entity.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Long> {
}
