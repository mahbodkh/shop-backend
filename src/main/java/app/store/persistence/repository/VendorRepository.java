package app.store.persistence.repository;

import app.store.persistence.domain.Vendor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, ObjectId> {
    Optional<Vendor> findOneById(ObjectId id);

}
