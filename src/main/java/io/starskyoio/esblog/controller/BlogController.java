package io.starskyoio.esblog.controller;

import io.starskyoio.esblog.common.Result;
import io.starskyoio.esblog.dto.BlogDTO;
import io.starskyoio.esblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/search")
    public Result search(String keyword, int pageNum, int pageSize) {
        long start = System.currentTimeMillis();
        List<BlogDTO> data = blogService.search("%" + keyword + "%", pageNum, pageSize);
        long cost = System.currentTimeMillis() - start;
        return Result.ok(cost, data);
    }

    @GetMapping("/es/search")
    public Result elasticSearch(String keyword, int pageNum, int pageSize) {
        long start = System.currentTimeMillis();
        List<BlogDTO> data = blogService.elasticSearch(keyword, pageNum, pageSize);
        long cost = System.currentTimeMillis() - start;
        return Result.ok(cost, data);
    }
}
