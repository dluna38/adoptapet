package co.lunadev.adoptaweb.services.mail.account;

import co.lunadev.adoptaweb.services.mail.EmailSender;
import co.lunadev.adoptaweb.services.mail.TemplatePath;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegistrationReceivedEmail extends EmailSender {

    public RegistrationReceivedEmail(JavaMailSender jMailSender, Configuration freeMarkerConfigurer) {
        super(jMailSender, freeMarkerConfigurer,
                TemplatePath.REGISTRATION_REQUEST_RECEIVED,
                "Petición de registro recibida - AdoptaPet");
    }


    public boolean send(String to, String username) {
        return this.sendSimpleMessage(to, Map.of("userName", username));
    }

}
