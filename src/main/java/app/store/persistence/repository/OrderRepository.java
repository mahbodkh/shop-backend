package app.store.persistence.repository;

import app.store.persistence.domain.Order;
import app.store.persistence.domain.enums.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

    Optional<Order> findByUserIdAndStatus(ObjectId userId, OrderStatus status);

    Page<Order> findAllByUserIdAndStatus(ObjectId userId, OrderStatus status, Pageable pageable);
}
