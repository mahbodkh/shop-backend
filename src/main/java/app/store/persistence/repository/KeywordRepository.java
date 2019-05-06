package app.store.persistence.repository;

import app.store.persistence.domain.Keyword;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface KeywordRepository extends MongoRepository<Keyword, ObjectId> {

    Optional<Keyword> findOneByName(String name);

    Optional<Keyword> findOneById(ObjectId id);

    @Async
    CompletableFuture<List<Keyword>> findAllByName(List<String> name);

}
