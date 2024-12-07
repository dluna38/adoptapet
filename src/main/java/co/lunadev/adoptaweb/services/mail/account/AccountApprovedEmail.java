package co.lunadev.adoptaweb.services.mail.account;

import co.lunadev.adoptaweb.services.mail.EmailSender;
import co.lunadev.adoptaweb.services.mail.TemplatePath;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountApprovedEmail extends EmailSender {

    public AccountApprovedEmail(JavaMailSender jMailSender, Configuration freeMarkerConfigurer,  @Qualifier("emailExecutor") TaskExecutor executor) {
        super(jMailSender, freeMarkerConfigurer, executor,
                TemplatePath.REGISTRATION_REQUEST_ACCEPTED,
                "Bienvenido(a) a AdoptaPet");
    }

    public AccountApprovedEmail body(String to, String username,String plainTempPassword) {
        setTo(to);
        setData(Map.of("userName", username,"plainTempPassword", plainTempPassword));
        return this;
    }
}
