package app.store.persistence.repository;

import app.store.persistence.domain.UserLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLogRepository extends MongoRepository<UserLog, ObjectId> {

    Optional<UserLog> findOneByUserId(ObjectId userId);

}
