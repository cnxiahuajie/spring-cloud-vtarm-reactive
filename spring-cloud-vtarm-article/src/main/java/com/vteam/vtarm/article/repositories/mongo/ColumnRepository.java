package com.vteam.vtarm.article.repositories.mongo;

import com.vteam.vtarm.article.domain.Column;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends ReactiveMongoRepository<Column, String>, ReactiveQueryByExampleExecutor<Column> {

}
