package app.store.persistence.repository;

import app.store.persistence.domain.Cart;
import app.store.persistence.domain.enums.CartStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, ObjectId> {

    Page<Cart> findAllByUserIdAndStatus(Pageable pageable, ObjectId userId, CartStatus status);

}
