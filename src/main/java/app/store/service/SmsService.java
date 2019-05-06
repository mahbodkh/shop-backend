package app.store.service;

import app.store.persistence.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final Logger log = LoggerFactory.getLogger(SmsService.class);


    public void sendActivationSms(User user) {

    }
}
