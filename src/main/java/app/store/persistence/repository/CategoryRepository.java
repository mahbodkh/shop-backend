package app.store.persistence.repository;

import app.store.persistence.domain.Category;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Optional<Category> findOneById(ObjectId id);

    Optional<Category> findOneByName(String name);

    Page<Category> findAllBy(Pageable pageable);

    @Async
    CompletableFuture<List<Category>> findAllByParentIsNullAndAncestorsNotNull();

    @Async
    CompletableFuture<List<Category>> findAllByAncestors(ObjectId id);

    @Async
    @Query(value = "{ $text: { $search: ?0 } }")
    CompletableFuture<List<Category>> findTextSearch(String value);


    List<Category> getAllBy(String text);


}
