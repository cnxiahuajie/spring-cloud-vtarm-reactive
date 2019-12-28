package com.vteam.vtarm.article.controller;

import com.vteam.vtarm.article.domain.Article;
import com.vteam.vtarm.article.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RequestMapping("v1/articles")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Mono<Article> findOne(@PathVariable("id") String id) {
        return articleService.findOne(id);
    }

}


