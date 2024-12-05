package co.lunadev.adoptaweb.services.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.Map;
import java.util.Objects;

@Log
public abstract class EmailSender {

    protected final JavaMailSender jMailSender;
    protected final freemarker.template.Configuration freeMarkerConfigurer;
    @Setter(value = AccessLevel.PACKAGE)
    protected String templatePath;
    @Setter(value = AccessLevel.PACKAGE)
    protected String subject;

    protected EmailSender(JavaMailSender jMailSender, Configuration freeMarkerConfigurer) {
        this.jMailSender = jMailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    protected EmailSender(JavaMailSender jMailSender, Configuration freeMarkerConfigurer, String templatePath, String subject) {
        this.jMailSender = jMailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
        this.templatePath = templatePath;
        this.subject = subject;
    }

    protected String fillTemplate(Map<String,Object> data) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(
                getTemplateFileMarker(), data);
    }

    protected boolean sendSimpleMessage(String to,Map<String,Object> data) {

        try {
            MimeMessageHelper helper = getMimeMsgHelper();
            helper.setTo(to);
            System.out.println(helper.getEncoding());
            helper.setSubject(this.subject);
            helper.setText(Objects.requireNonNull(fillTemplate(data),"fallo cargando el email template"), true);
            jMailSender.send(helper.getMimeMessage());
            return true;
        } catch (MessagingException | MailException | NullPointerException e) {
            log.severe("error enviando email------------------\n"+e);
        }catch (IOException | TemplateException e) {
            log.severe("error construyendo el cuerpo del correo------------------\n"+e);
        }
        return false;
    }

    private MimeMessageHelper getMimeMsgHelper() throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(jMailSender.createMimeMessage());
        helper.setFrom("info@adoptapet.com");
        return helper;
    }

    protected String getTemplateFile(){
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
    protected Template getTemplateFileMarker() throws IOException {
        return freeMarkerConfigurer.getTemplate(templatePath);
    }
}

