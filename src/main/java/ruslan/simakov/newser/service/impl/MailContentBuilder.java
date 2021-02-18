package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Scope("prototype")
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String buildEmailText(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
