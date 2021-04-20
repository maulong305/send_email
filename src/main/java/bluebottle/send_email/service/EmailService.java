package bluebottle.send_email.service;

import bluebottle.send_email.dto.EmailRequest;
import bluebottle.send_email.model.Email;
import com.wildbit.java.postmark.client.exception.PostmarkException;

import java.io.IOException;
import java.util.List;

public interface EmailService {
    Email findById(Long id);
    List<Email> findAll();
    Boolean remove(Long id);
    Email save(Email email);
    String sendEmail(EmailRequest emailRequest);
    void senUseSDK(EmailRequest emailRequest) throws IOException, PostmarkException;

}
