package bluebottle.send_email.service;

import bluebottle.send_email.model.Email;
import bluebottle.send_email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    EmailRepository emailRepository;
    @Override
    public Email findById(Long id) {
        return emailRepository.findById(id).orElse(null);
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public Boolean remove(Long id) {
        emailRepository.deleteById(id);
        return true;
    }

    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    private final String fromEmail = "hieu.truong@fruitful.io";
//    private final String token = "ea5851c8-5682-45ac-886d-389594821847";
//    private final String apiLink = "https://api.postmarkapp.com/email";
//    @Override
//    public String sendEmail(Email email) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/json");
//        headers.add("Content-Type", "application/json");
//        headers.add("X-Postmark-Server-Token", token);
//        email.setFromMail(fromEmail);
//        HttpEntity<Email> entity = new HttpEntity<>(email, headers);
//        return restTemplate.exchange(apiLink, HttpMethod.POST, entity, String.class).getBody();
//    }


}
