package com.vteam.vtarm.library.router;

import com.vteam.vtarm.library.handler.LibraryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LibraryRouter {

    @Bean
    public RouterFunction<ServerResponse> routeArticle(LibraryHandler libraryHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/findArticlesByCategory/{category}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), libraryHandler::findByCategory)
                .andRoute(RequestPredicates.POST("/saveArticle").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), libraryHandler::saveArticle)
                .andRoute(RequestPredicates.DELETE("/removeArticle/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), libraryHandler::removeArticle)
                .andRoute(RequestPredicates.GET("/getArticle/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), libraryHandler::getArticle);
    }

}
