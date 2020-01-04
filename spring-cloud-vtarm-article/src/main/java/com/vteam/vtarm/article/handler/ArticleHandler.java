package com.vteam.vtarm.article.handler;

import com.vteam.vtarm.article.domain.Article;
import com.vteam.vtarm.article.repositories.mongo.ArticleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 文章处理器
 */
@Component
public class ArticleHandler {

    @Resource
    private ArticleRepository articleRepository;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Article.class).flatMap(article -> ServerResponse.ok().body(articleRepository.save(article), Article.class));
    }

    public Mono<ServerResponse> findOne(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(articleRepository.findById(id), Article.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(articleRepository.findAll(), Article.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(articleRepository.deleteById(id), Void.class);
    }

    public Mono<ServerResponse> findByAuthor(ServerRequest serverRequest) {
        String author = serverRequest.pathVariable("author");
        Article article = Article.builder().author(author).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);
        return ServerResponse.ok().body(articleRepository.findAll(example), Article.class);
    }

    public Mono<ServerResponse> findByCategory(ServerRequest serverRequest) {
        String category = serverRequest.pathVariable("category");
        Article article = Article.builder().category(category).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);
        return ServerResponse.ok().body(articleRepository.findAll(example), Article.class);
    }

    public Mono<ServerResponse> findByColumn(ServerRequest serverRequest) {
        String column = serverRequest.pathVariable("column");
        Article article = Article.builder().column(column).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("column", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIncludeNullValues();

        Example<Article> example = Example.of(article, matcher);
        return ServerResponse.ok().body(articleRepository.findAll(example), Article.class);
    }

    public Mono<ServerResponse> findByKeyword(ServerRequest serverRequest) {
        String keyword = serverRequest.pathVariable("keyword");
        return ServerResponse.ok().build();
    }

}
