package app.store.persistence.repository;

import app.store.persistence.domain.Bank;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, ObjectId> {
}
