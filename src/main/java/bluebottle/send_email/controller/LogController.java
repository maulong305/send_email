package bluebottle.send_email.controller;

import bluebottle.send_email.model.SpringLoggingHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @RequestMapping("/")
    String index(){
        new SpringLoggingHelper().helpMethod();
        return "index";
    } 
}
