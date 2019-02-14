package app.store.persistence.repository;

import app.store.persistence.domain.Bank;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface BankRepository extends MongoRepository<Bank, ObjectId> {

    Optional<Bank> findBankByName(String name);

    @Async
    CompletableFuture<List<Bank>> getAllBy();
}
