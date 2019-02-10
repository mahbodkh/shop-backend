package app.store.persistence.repository;

import app.store.persistence.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
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
    CompletableFuture<List<Product>> findAllByCategories(ObjectId id);


}
