package co.lunadev.adoptaweb.services.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
public class RegistrationReceivedEmail extends EmailSender{

    private String username;
    public RegistrationReceivedEmail(JavaMailSender emailSender) {
        super(emailSender);
        this.setTemplatePath(TemplatePath.REGISTRATION_REQUEST_RECEIVED);
        this.setSubject("Petición de registro recibida - AdoptaPet");
    }

    @Override
    String fillTemplate() {
        return String.format(getTemplate(),this.username);
    }


    public boolean sendMail(String username,String to){
        setUsername(username);
        return this.sendSimpleMessage(to);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
