package app.store.persistence.repository;

import app.store.persistence.domain.Invoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, ObjectId> {

    Optional<Invoice> findOneById(ObjectId id);

}
