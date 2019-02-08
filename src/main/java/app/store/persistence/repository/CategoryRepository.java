package app.store.persistence.repository;

import app.store.persistence.domain.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Optional<Category> findOneById(ObjectId id);

    Optional<Category> findOneByName(String name);

    @Async
    CompletableFuture<List<Category>> findAllByAncestors(ObjectId id);

}
