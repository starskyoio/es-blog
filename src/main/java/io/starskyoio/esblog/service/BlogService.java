package io.starskyoio.esblog.service;

import io.starskyoio.esblog.dto.BlogDTO;
import io.starskyoio.esblog.entity.Blog;
import io.starskyoio.esblog.entity.EsBlog;
import io.starskyoio.esblog.repository.BlogRepository;
import io.starskyoio.esblog.repository.EsBlogRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final EsBlogRepository esBlogRepository;

    public List<BlogDTO> search(String keyword, int pageNum, int pageSize) {
        List<Blog> list = blogRepository.findByKeyword(keyword,  PageRequest.of(pageNum, pageSize));
        return list.stream().map(this::toDTO).collect(toList());
    }

    public List<BlogDTO> elasticSearch(String keyword, int pageNum, int pageSize) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.matchPhraseQuery("title", keyword));
        queryBuilder.should(QueryBuilders.matchPhraseQuery("content", keyword));
        SortBuilder sortBuilder = SortBuilders.fieldSort("update_time").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(pageNum, pageSize))
                .build();
        Page<EsBlog> page = esBlogRepository.search(searchQuery);
        return page.getContent().stream().map(this::toDTO).collect(toList());
    }


    public BlogDTO toDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setAuthor(blog.getAuthor());
        blogDTO.setCreateTime(blog.getCreateTime());
        blogDTO.setUpdateTime(blog.getUpdateTime());
        return blogDTO;
    }

    public BlogDTO toDTO(EsBlog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setAuthor(blog.getAuthor());
        blogDTO.setCreateTime(blog.getCreateTime());
        blogDTO.setUpdateTime(blog.getUpdateTime());
        return blogDTO;
    }
}
