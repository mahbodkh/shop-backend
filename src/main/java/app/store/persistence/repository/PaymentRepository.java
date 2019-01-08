package app.store.persistence.repository;

import app.store.persistence.domain.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, ObjectId> {

    Optional<Payment> findOneById(ObjectId id);

}
