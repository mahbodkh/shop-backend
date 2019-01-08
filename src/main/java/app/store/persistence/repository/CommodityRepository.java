package app.store.persistence.repository;

import app.store.persistence.domain.Commodity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommodityRepository extends MongoRepository<Commodity, ObjectId> {

    Optional<Commodity> findOneById(ObjectId id);


}
