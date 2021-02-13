package ruslan.simakov.newser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEmail {
    private String subject = "Please activate your account";
    private String recipient;
    private String body = "Thank you that SigningUp! Please activate your account: " +
            "http://localhost:8080/api/auth/accountVerification/";

    public NotificationEmail(String recipient, String token) {
        this.recipient = recipient;
        body += token;
    }
}
