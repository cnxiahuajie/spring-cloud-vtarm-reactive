package com.vteam.vtarm.article.api.router;

import com.vteam.vtarm.article.api.handler.ArticleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 文章路由服务
 */
@Configuration
public class ArticleRouter {

    @Bean
    public RouterFunction<ServerResponse> routeArticle(ArticleHandler articleHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findAll)
                .andRoute(RequestPredicates.GET("/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findOne)
                .andRoute(RequestPredicates.GET("/_author/{author}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findByAuthor)
                .andRoute(RequestPredicates.GET("/_category/{category}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findByCategory)
                .andRoute(RequestPredicates.GET("/_column/{column}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findByColumn)
                .andRoute(RequestPredicates.GET("/_search/{keyword}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::findByKeyword)
                .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::save)
                .andRoute(RequestPredicates.DELETE("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), articleHandler::delete);
    }

}
