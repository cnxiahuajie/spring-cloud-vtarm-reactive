package com.vteam.vtarm.library.handler;

import com.vteam.vtarm.library.domain.Article;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 文章控制器
 */
@Component
public class LibraryHandler {

    @Resource(name = "articleClient")
    private WebClient articleClient;

    /**
     * 按文章目录查询文章列表
     *
     * @param serverRequest 请求信息
     * @return 响应信息
     */
    public Mono<ServerResponse> findByCategory(ServerRequest serverRequest) {
        String category = serverRequest.pathVariable("category");
        Flux<Article> articleFlux = articleClient.get().uri("_category/{category}", category).retrieve().bodyToFlux(Article.class);
        return ServerResponse.ok().body(articleFlux, Article.class);
    }

    /**
     * 保存一篇文章
     *
     * @param serverRequest 请求信息
     * @return 响应信息
     */
    public Mono<ServerResponse> saveArticle(ServerRequest serverRequest) {
        Mono<Article> requestArticleMono = serverRequest.bodyToMono(Article.class);
        Mono<Article> responseArticleMono = articleClient.post().uri("").body(requestArticleMono, Article.class).retrieve().bodyToMono(Article.class);
        return ServerResponse.ok().body(responseArticleMono, Article.class);
    }

    /**
     * 删除一篇文章
     *
     * @param serverRequest 请求信息
     * @return 响应信息
     */
    public Mono<ServerResponse> removeArticle(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Void> response = articleClient.delete().uri("/{id}", id).retrieve().bodyToMono(Void.class);
        return ServerResponse.ok().body(response, Void.class);
    }

    /**
     * 查询一篇文章
     *
     * @param serverRequest 请求信息
     * @return 响应信息
     */
    public Mono<ServerResponse> getArticle(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Article> response = articleClient.get().uri("/{id}", id).retrieve().bodyToMono(Article.class);
        return ServerResponse.ok().body(response, Article.class);
    }

}
