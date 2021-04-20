package bluebottle.send_email.repository;

import bluebottle.send_email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
