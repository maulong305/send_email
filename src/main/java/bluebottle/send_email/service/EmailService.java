package bluebottle.send_email.service;

import bluebottle.send_email.model.Email;
import com.wildbit.java.postmark.client.exception.PostmarkException;

import java.io.IOException;
import java.util.List;

public interface EmailService {
    Email findById(Long id);
    List<Email> findAll();
    Boolean remove(Long id);
    Email save(Email email);
    String sendEmail(Email email);
    void senUseSDK(Email email) throws IOException, PostmarkException;

}
