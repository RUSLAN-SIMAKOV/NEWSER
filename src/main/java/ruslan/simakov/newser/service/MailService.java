package ruslan.simakov.newser.service;

import ruslan.simakov.newser.model.NotificationEmail;

public interface MailService {
    void sendEmail(NotificationEmail notificationEmail);
}
