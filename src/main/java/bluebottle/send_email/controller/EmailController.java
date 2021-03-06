package bluebottle.send_email.controller;

import bluebottle.send_email.dto.EmailRequest;
import bluebottle.send_email.dto.EmailResponse;
import bluebottle.send_email.model.Email;
import bluebottle.send_email.service.EmailService;
import com.wildbit.java.postmark.client.exception.PostmarkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<EmailRequest> sendEmail(@RequestBody EmailRequest emailRequest){
        emailService.sendEmail(emailRequest);
        return new ResponseEntity<>(emailRequest, HttpStatus.OK);
    }

    @PostMapping("/sendUseSDK")
    public ResponseEntity<Void> sendUseSDK(@RequestBody EmailRequest emailRequest) throws IOException, PostmarkException {
        emailService.senUseSDK(emailRequest);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
