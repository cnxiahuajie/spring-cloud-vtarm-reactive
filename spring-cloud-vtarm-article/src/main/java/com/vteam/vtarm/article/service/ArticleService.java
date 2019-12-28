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
        Article article = Article.builder().author(author).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);

        Flux<Article> articles = articleRepository.findAll(example).log("findByAuthor");

        return articles;
    }

    public Flux<Article> findByCategory(String category) {
        Article article = Article.builder().category(category).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);

        Flux<Article> articles = articleRepository.findAll(example).log("findByCategory");

        return articles;
    }

    public Flux<Article> findByColumn(String column) {
        Article article = Article.builder().column(column).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("column", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);

        Flux<Article> articles = articleRepository.findAll(example).log("findByColumn");

        return articles;
    }

    public Flux<Article> findByKeyword(String keyword) {
        // TODO elasticsearch
        return Flux.just(Article.builder().title("暂未实现").build());
    }

}
