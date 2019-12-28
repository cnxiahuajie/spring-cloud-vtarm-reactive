package com.vteam.vtarm.article.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 专栏
 */
@Builder
@Data
@Document(collection = "column")
public class Column {

    /**
     * 主键
     */
    @MongoId
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 分类
     */
    private String category;

}
