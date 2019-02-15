package app.store.persistence.repository;

import app.store.persistence.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Optional<Product> findOneById(ObjectId id);

    Optional<Product> findOneByTitle(String title);

    @Async
    @Query(value = "{ 'keywords.name' : ?0 }")
    CompletableFuture<List<Product>> findAllByKeywordsName(String name);

    @Async
    CompletableFuture<List<Product>> findAllByCategories(ObjectId id);

    @Async
//    @Query(value = "{ $text: {$search: ?0 } }")
    CompletableFuture<List<Product>> findAllBy(TextCriteria text);


}
