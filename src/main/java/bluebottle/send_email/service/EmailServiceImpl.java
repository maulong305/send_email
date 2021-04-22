package bluebottle.send_email.service;

import bluebottle.send_email.dto.EmailRequest;
import bluebottle.send_email.model.Email;
import bluebottle.send_email.repository.EmailRepository;
import com.wildbit.java.postmark.Postmark;
import com.wildbit.java.postmark.client.ApiClient;
import com.wildbit.java.postmark.client.data.model.message.Message;
import com.wildbit.java.postmark.client.data.model.message.MessageResponse;
import com.wildbit.java.postmark.client.exception.PostmarkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${fromMail}")
    private String fromEmail;
    @Value("${token}")
    private String token;
    @Value("${apiLink}")
    private String apiLink;

    @Override
    public String sendEmail(EmailRequest emailRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("X-Postmark-Server-Token", token);
        emailRequest.setFromMail(fromEmail);
        HttpEntity<EmailRequest> entity = new HttpEntity<>(emailRequest, headers);
        return restTemplate.exchange(apiLink, HttpMethod.POST, entity, String.class).getBody();
    }

    @Override
    public void senUseSDK(EmailRequest emailRequest) throws IOException, PostmarkException {
        ApiClient client = Postmark.getApiClient(token);
        Message message = new Message();
        message.setFrom(fromEmail);
        message.setSubject(emailRequest.getSubject());
        message.setTo(emailRequest.getToMail());
        message.setHtmlBody(emailRequest.getHtmlBody());
        MessageResponse messageResponse = client.deliverMessage(message);
    }
}
