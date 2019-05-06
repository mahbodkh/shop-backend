package app.store.persistence.repository;

import app.store.persistence.domain.Cart;
import app.store.persistence.domain.enums.CartStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, ObjectId> {

    Optional<Cart> findOneByIdAndStatus(ObjectId id, CartStatus status);

    Optional<Cart> findOneByUserIdAndStatus(ObjectId userId, CartStatus status);

    Page<Cart> findAllByUserIdAndStatus(ObjectId userId, CartStatus status, Pageable pageable);

}
