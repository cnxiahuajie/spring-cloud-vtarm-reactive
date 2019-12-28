package com.vteam.vtarm.article.runner;

import com.vteam.vtarm.article.domain.Article;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;

/**
 * 初始化数据库
 */
@Component
public class InitDatabase implements CommandLineRunner {

    @Resource
    private MongoOperations mongoOperations;

    @Override
    public void run(String... args) throws Exception {
        if (Arrays.asList(args).contains("--initDB")) {
            mongoOperations.dropCollection(Article.class);

            for (int i = 1; i < 3; i++) {
                mongoOperations.insert(new Article(UUID.randomUUID().toString(), String.format("实例文章(%d)", i), String.format("实例内容(%d)", i), String.format("实例作者(%d)", i)));
            }

            mongoOperations.findAll(Article.class).forEach(System.out::println);
        }
    }

}
