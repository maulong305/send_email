package bluebottle.send_email.dto;

import bluebottle.send_email.model.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class EmailResponse {
    private String fromMail;
    private String toMail;
    private String subject;
    private String htmlBody;

    public EmailResponse(Email email) {
        BeanUtils.copyProperties(email, this);
    }
}
