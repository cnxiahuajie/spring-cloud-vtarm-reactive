package com.vteam.vtarm.article.api.handler;

import com.vteam.vtarm.article.domain.Article;
import com.vteam.vtarm.article.service.ArticleService;
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
    private ArticleService articleService;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Article.class).flatMap(article -> ServerResponse.ok().body(articleService.save(article), Article.class));
    }

    public Mono<ServerResponse> findOne(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(articleService.findOne(id), Article.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(articleService.findAll(), Article.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(articleService.delete(id), Void.class);
    }

    public Mono<ServerResponse> findByAuthor(ServerRequest serverRequest) {
        String author = serverRequest.pathVariable("author");
        return ServerResponse.ok().body(articleService.findByAuthor(author), Article.class);
    }

    public Mono<ServerResponse> findByCategory(ServerRequest serverRequest) {
        String category = serverRequest.pathVariable("category");
        return ServerResponse.ok().body(articleService.findByCategory(category), Article.class);
    }

    public Mono<ServerResponse> findByColumn(ServerRequest serverRequest) {
        String column = serverRequest.pathVariable("column");
        return ServerResponse.ok().body(articleService.findByColumn(column), Article.class);
    }

    public Mono<ServerResponse> findByKeyword(ServerRequest serverRequest) {
        String keyword = serverRequest.pathVariable("keyword");
        return ServerResponse.ok().body(articleService.findByKeyword(keyword), Article.class);
    }

}
