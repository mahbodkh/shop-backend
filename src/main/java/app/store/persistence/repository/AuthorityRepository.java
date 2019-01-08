package app.store.persistence.repository;


import app.store.persistence.domain.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends MongoRepository<Authority, String> {
    Optional<Authority> findByName(String role);
}
