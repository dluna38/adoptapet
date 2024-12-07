package co.lunadev.adoptaweb.services.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.config.Task;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.Map;
import java.util.Objects;

@Log
@Getter
@Setter
public abstract class EmailSender implements Runnable{

    protected final JavaMailSender jMailSender;
    protected final freemarker.template.Configuration freeMarkerConfigurer;
    protected String templatePath;
    protected String subject;
    protected String to;
    protected Map<String,Object> data;
    private final TaskExecutor taskExecutor;

    protected EmailSender(JavaMailSender jMailSender, Configuration freeMarkerConfigurer, TaskExecutor taskExecutor) {
        this.jMailSender = jMailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;

        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run() {
        sendSimpleMessage();
    }

    protected EmailSender(JavaMailSender jMailSender, Configuration freeMarkerConfigurer, TaskExecutor taskExecutor,String templatePath, String subject) {
        this.jMailSender = jMailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
        this.templatePath = templatePath;
        this.subject = subject;
        this.taskExecutor = taskExecutor;
    }

    /**
     *  Envia el correo agregandolo al hilo de envio de emails
     */
    public void execute(){
        this.taskExecutor.execute(this);
    }

    protected String fillTemplate() throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(
                getTemplateFileMarker(), this.data);
    }

    protected boolean sendSimpleMessage() {
        try {
            MimeMessageHelper helper = getMimeMsgHelper();
            helper.setTo(this.to);
            helper.setSubject(this.subject);
            helper.setText(Objects.requireNonNull(fillTemplate(),"fallo cargando el email template"), true);
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

