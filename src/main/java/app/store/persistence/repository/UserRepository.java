package app.store.persistence.repository;

import app.store.persistence.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findOneById(ObjectId id);

    Optional<User> findOneByActivationKey(String activationKey);

    Optional<User> findByMobileAndActivationKey(String mobile, String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByEmailIgnoreCaseAndActivationKey(String email, String activationKey);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByMobile(String mobile);

    Optional<User> findOneByMobileAndActivatedFalse(String mobile);

    Optional<User> findOneByMobileAndActivatedTrue(String mobile);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    Optional<User> findByLogin(String username);


}
