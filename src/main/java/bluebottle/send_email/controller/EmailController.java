package bluebottle.send_email.controller;

import bluebottle.send_email.model.Email;
import bluebottle.send_email.service.EmailService;
import com.wildbit.java.postmark.Postmark;
import com.wildbit.java.postmark.client.ApiClient;
import com.wildbit.java.postmark.client.data.model.message.Message;
import com.wildbit.java.postmark.client.data.model.message.MessageResponse;
import com.wildbit.java.postmark.client.exception.PostmarkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {
//    public static void main(String[] args) throws IOException, PostmarkException {
//        ApiClient client = Postmark.getApiClient("ea5851c8-5682-45ac-886d-389594821847");
//        Message message = new Message("hieu.truong@fruitful.io",
//                "long.nguyenmau@gmail.com",
//                "Test call API Postmark",
//                "Xin chào, bạn đã gửi thành công 1 email.");
//        MessageResponse messageResponse = client.deliverMessage(message);
//    }

    private final String fromEmail = "hieu.truong@fruitful.io";
    private final String token = "ea5851c8-5682-45ac-886d-389594821847";
    private final String apiLink = "https://api.postmarkapp.com/email";

    @Autowired
    private EmailService emailService;
    @PostMapping("/create")
    public ResponseEntity<Email> create(@RequestBody Email email) throws IOException, PostmarkException {
        emailService.save(email);
        ApiClient client = Postmark.getApiClient(token);
        Message message = new Message();
        message.setFrom(fromEmail);
        message.setSubject(email.getSubject());
        message.setTo(email.getToMail());
        message.setHtmlBody(email.getHtmlBody());
        MessageResponse messageResponse = client.deliverMessage(message);
        System.out.println(messageResponse);
        return new  ResponseEntity<>(email, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Email>> getAll(){
        List<Email> emails = emailService.findAll();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("X-Postmark-Server-Token", token);
        email.setFromMail(fromEmail);
        HttpEntity<Email> entity = new HttpEntity<>(email, headers);
        return restTemplate.exchange( apiLink, HttpMethod.POST, entity, String.class).getBody();
    }
}
