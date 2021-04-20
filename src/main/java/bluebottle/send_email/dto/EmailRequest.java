package bluebottle.send_email.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailRequest {
    @JsonProperty(value = "From")
    private String fromMail;
    @JsonProperty(value = "To")
    private String toMail;
    @JsonProperty(value = "Subject")
    private String subject;
    @JsonProperty(value = "HtmlBody")
    private String htmlBody;
}
