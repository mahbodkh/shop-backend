package app.store.service;

import app.store.persistence.domain.ProductIdAndTime;
import app.store.persistence.domain.User;
import app.store.persistence.domain.UserLog;
import app.store.persistence.repository.UserLogRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserLogService {
    private final Logger log = LoggerFactory.getLogger(UserLogService.class);

    private final UserLogRepository userLogRepository;
    private final UserService userService;

    public UserLogService(UserLogRepository userLogRepository, UserService userService) {

        this.userLogRepository = userLogRepository;
        this.userService = userService;
    }


    @Async
    public void createProductLog(String username, String productId) {
        Optional<User> user = userService.getUsername(username);
        if (user.isPresent()) {

            Optional<UserLog> logsByUser = userLogRepository.findOneByUserId(user.get().getId());
            if (logsByUser.isPresent()) {
                UserLog logs = logsByUser.get();
                ProductIdAndTime productIdAndTime = new ProductIdAndTime();
                List<ProductIdAndTime> productIdAndTimes = logs.getProductIdAndTimes();

                productIdAndTime.setProductId(new ObjectId(productId));

                productIdAndTime.setTime(Instant.now());
                productIdAndTimes.add(productIdAndTime);
                logs.setProductIdAndTimes(productIdAndTimes);

                log.debug("Persistence UserLog: {}", logs);
                userLogRepository.save(logs);
            } else {

                UserLog userLog = new UserLog();
                ProductIdAndTime productIdAndTime = new ProductIdAndTime();
                List<ProductIdAndTime> productIdAndTimes = new CopyOnWriteArrayList<>();

                userLog.setUserId(user.get().getId());
                productIdAndTime.setProductId(new ObjectId(productId));
                productIdAndTime.setTime(Instant.now());
                productIdAndTimes.add(productIdAndTime);
                userLog.setProductIdAndTimes(productIdAndTimes);
                log.debug("Update UserLog: {}", userLog);
                userLogRepository.save(userLog);

            }
        }
    }


}
