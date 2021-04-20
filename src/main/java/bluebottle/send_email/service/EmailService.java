package bluebottle.send_email.service;

import bluebottle.send_email.model.Email;

import java.util.List;

public interface EmailService {
    Email findById(Long id);
    List<Email> findAll();
    Boolean remove(Long id);
    Email save(Email email);
//    String sendEmail(Email email);
}