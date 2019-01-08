package app.store.persistence.repository;

import app.store.persistence.domain.Brand;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, ObjectId> {
    Optional<Brand> findOneById(ObjectId id);

}
