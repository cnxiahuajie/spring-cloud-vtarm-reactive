package com.vteam.vtarm.article.repositories.mongo;

import com.vteam.vtarm.article.domain.Article;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String>, ReactiveQueryByExampleExecutor<Article> {

}
