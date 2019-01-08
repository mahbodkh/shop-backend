package app.store.service;


import app.store.persistence.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isExists(String userId) {
        return userRepository.existsById(new ObjectId(userId));
    }
}
