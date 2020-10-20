package io.starskyoio.esblog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BlogDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private Date updateTime;
}
