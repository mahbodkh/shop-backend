package app.store.persistence.repository;

import app.store.persistence.domain.Bank;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Bank, ObjectId> {
}
