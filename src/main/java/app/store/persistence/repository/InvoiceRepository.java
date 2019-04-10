package app.store.persistence.repository;

import app.store.persistence.domain.Invoice;
import app.store.persistence.domain.enums.InvoiceStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, ObjectId> {

    Optional<Invoice> findOneById(ObjectId id);

//    Page<Invoice> findAllByStatus(InvoiceStatus status);

//    Page<Invoice> findAllByUserIdAndStatus(ObjectId userId, InvoiceStatus status);

//    List<Invoice> findAllByUserIdAndStatus(ObjectId userId, InvoiceStatus status);

}
