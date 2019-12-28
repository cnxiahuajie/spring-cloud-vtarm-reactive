package com.vteam.vtarm.article.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 分类
 */
@Builder
@Data
@Document(collection = "category")
public class Category {

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
     * 创建人
     */
    private String creator;

    /**
     * 图标
     */
    private String icon;

}
