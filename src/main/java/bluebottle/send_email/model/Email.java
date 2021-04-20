package bluebottle.send_email.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(value = "From")
    private String fromMail;
    @JsonProperty(value = "To")
    private String toMail;
    @JsonProperty(value = "Subject")
    private String subject;
    @JsonProperty(value = "HtmlBody")
    private String htmlBody;
  }
