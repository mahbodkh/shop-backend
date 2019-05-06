package app.store.service;

import app.store.persistence.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final Logger log = LoggerFactory.getLogger(MailService.class);

//    private final JavaMailSender javaMailSender;
//    private final MessageSource messageSource;
//    private final SpringTemplateEngine templateEngine;


    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
    }


    @Async
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
    }

}