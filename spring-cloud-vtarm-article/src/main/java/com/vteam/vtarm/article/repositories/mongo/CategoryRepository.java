package com.vteam.vtarm.article.repositories.mongo;

import com.vteam.vtarm.article.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String>, ReactiveQueryByExampleExecutor<Category> {

}
