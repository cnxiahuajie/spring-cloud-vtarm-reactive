package com.vteam.vtarm.article.runner;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.article.domain.Article;
import com.vteam.vtarm.article.domain.Category;
import com.vteam.vtarm.article.domain.Column;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(String... args) throws Exception {
        if (Arrays.asList(args).contains("--initDB")) {

            // 初始化分类
            mongoOperations.dropCollection(Category.class);
            Category.CategoryBuilder categoryBuilder = Category.builder();
            for (int i = 1; i < 10; i++) {
                mongoOperations.insert(categoryBuilder.id(UUID.randomUUID().toString()).name(String.format("示例名称(%d)", i))
                        .icon(String.format("示例图标(%d)", i))
                        .creator(applicationName).build());
            }

            // 初始化专栏
            mongoOperations.dropCollection(Column.class);
            mongoOperations.findAll(Category.class).forEach(category -> {
                System.out.println(JSONObject.toJSONString(category));
                Column.ColumnBuilder columnBuilder = Column.builder();
                for (int i = 1; i < 10; i++) {
                    mongoOperations.insert(columnBuilder.id(UUID.randomUUID().toString())
                            .name(String.format("示例专栏(%d)", i))
                            .icon(String.format("示例图标(%d)", i))
                            .category(category.getId())
                            .creator(applicationName).build());
                }
            });

            // 初始化文章
            mongoOperations.dropCollection(Article.class);
            mongoOperations.findAll(Column.class).forEach(column -> {
                System.out.println(JSONObject.toJSONString(column));
                Article.ArticleBuilder articleBuilder = Article.builder();
                for (int i = 1; i < 10; i++) {
                    mongoOperations.insert(articleBuilder.id(UUID.randomUUID().toString())
                            .title(String.format("实例文章(%d)", i))
                            .content(String.format("实例内容(%d)", i))
                            .category(column.getCategory())
                            .column(column.getId())
                            .author(String.format("示例作者(%d)", i))
                            .creator(applicationName)
                            .type("M")
                            .tags(Arrays.asList(String.format("标签(%d)", i))).build());
                }
            });

            mongoOperations.findAll(Article.class).forEach(System.out::println);
        }
    }

}
