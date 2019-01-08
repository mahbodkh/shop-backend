package app.store.persistence.repository;

import app.store.persistence.domain.Keyword;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends MongoRepository<Keyword, ObjectId> {

    Optional<Keyword> findOneById(ObjectId id);

}
