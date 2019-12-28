package com.vteam.vtarm.article.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

/**
 * 文章
 */
@Builder
@Data
@Document(collection = "article")
public class Article {

    /**
     * 主键
     */
    @MongoId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章类型（M=markdown,R=富文本）
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 作者
     */
    private String author;

    /**
     * 分类
     */
    private String category;

    /**
     * 专栏
     */
    private String column;

    /**
     * 标签列表
     */
    private List<String> tags;

}
