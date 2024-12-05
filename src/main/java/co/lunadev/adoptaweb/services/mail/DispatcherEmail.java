package co.lunadev.adoptaweb.services.mail;


import co.lunadev.adoptaweb.services.mail.account.AccountApprovedEmail;
import co.lunadev.adoptaweb.services.mail.account.RegistrationReceivedEmail;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DispatcherEmail extends EmailSender {

    public DispatcherEmail(JavaMailSender jMailSender, Configuration freeMarkerConfigurer) {
        super(jMailSender, freeMarkerConfigurer);
    }

    public RegistrationReceivedEmail registrationReceivedEmail(){
        return new RegistrationReceivedEmail(this.jMailSender,
               this.freeMarkerConfigurer);
    }

    public AccountApprovedEmail accountApprovedEmail(){
        return new AccountApprovedEmail(this.jMailSender,
                this.freeMarkerConfigurer);
    }

}
