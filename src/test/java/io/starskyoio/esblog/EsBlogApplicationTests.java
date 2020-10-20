package io.starskyoio.esblog;

import io.starskyoio.esblog.entity.Blog;
import io.starskyoio.esblog.entity.EsBlog;
import io.starskyoio.esblog.repository.BlogRepository;
import io.starskyoio.esblog.repository.EsBlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogApplicationTests {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Test
    public void contextLoads() {
//        final int num = 1000000;
//        final int batchSize = 1000;
//        List<Blog> list = new ArrayList<>(batchSize);
//        Blog blog;
//        for (int i = 0; i < num; i++) {
//            blog = new Blog();
//            blog.setTitle("测试标题"+i);
//            blog.setContent("测试内容"+i);
//            blog.setAuthor("测试作者"+i);
//            blog.setCreateTime(new Date());
//            blog.setUpdateTime(new Date());
//            list.add(blog);
//            if(list.size() == batchSize){
//                blogRepository.saveAll(list);
//                list.clear();
//            }
//        }


        Optional<EsBlog> optional = esBlogRepository.findById(1L);
        System.out.println(optional.get());
    }

}
