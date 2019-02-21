package app.store.persistence.repository;

import app.store.persistence.domain.Configuration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends MongoRepository<Configuration, ObjectId> {


}
