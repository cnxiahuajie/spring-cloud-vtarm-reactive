package com.vteam.vtarm.article.service;

import com.vteam.vtarm.article.domain.Article;
import com.vteam.vtarm.article.repositories.mongo.ArticleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> save(Article article) {
        return articleRepository.save(article);
    }

    public Mono<Article> findOne(String id) {
        return articleRepository.findById(id).log("findOneArticle");
    }

    public Flux<Article> findAll() {
        return articleRepository.findAll().log("findAll");
    }

    public Mono<Void> delete(String id) {
        return articleRepository.deleteById(id).log("deleteOneArticle");
    }

    public Flux<Article> findByAuthor(String author) {
        Article article = new Article();
        article.setAuthor(author);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher(author, ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);

        Flux<Article> articles = articleRepository.findAll(example).log("findByAuthor");

        return articles;
    }

}
