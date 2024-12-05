package co.lunadev.adoptaweb.services.mail.account;

import co.lunadev.adoptaweb.services.mail.EmailSender;
import co.lunadev.adoptaweb.services.mail.TemplatePath;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountApprovedEmail extends EmailSender {

    public AccountApprovedEmail(JavaMailSender jMailSender, Configuration freeMarkerConfigurer) {
        super(jMailSender, freeMarkerConfigurer,
                TemplatePath.REGISTRATION_REQUEST_ACCEPTED,
                "Bienvenido(a) a AdoptaPet");
    }


    public boolean send(String to, String username,String plainTempPassword) {
        return this.sendSimpleMessage(to, Map.of("userName", username,"plainTempPassword", plainTempPassword));
    }
}
