package co.lunadev.adoptaweb.services.mail;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

@Component
@Log
public abstract class EmailSender {

    private final JavaMailSender jMailSender;
    @Setter(value = AccessLevel.PACKAGE)
    protected String templatePath;
    @Setter(value = AccessLevel.PACKAGE)
    protected String subject;

    protected EmailSender(JavaMailSender emailSender) {
        this.jMailSender = emailSender;
    }

    abstract String fillTemplate();

    protected boolean sendSimpleMessage(String to) {

        try {
            MimeMessageHelper helper = getMimeMsgHelper();
            helper.setTo(to);
            helper.setSubject(this.subject);
            helper.setText(Objects.requireNonNull(fillTemplate(),"fallo cargando el email template"), true);
            jMailSender.send(helper.getMimeMessage());
            return true;
        } catch (MessagingException | MailException | NullPointerException e) {
            log.severe("error enviando mensaje------------------\n"+e);
        }
        return false;
    }

    private MimeMessageHelper getMimeMsgHelper() throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(jMailSender.createMimeMessage());
        helper.setFrom("dluna217@hotmail.com");
        return helper;
    }

    protected String getTemplate(){
        if(templatePath == null){
            return null;
        }
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(this.templatePath))){
            StringBuilder contentBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            log.severe("fail to get the template: "+this.templatePath+"; exception: "+e.getMessage());
            return null;
        }
    }

}

