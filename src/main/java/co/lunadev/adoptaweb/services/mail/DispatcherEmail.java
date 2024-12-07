package co.lunadev.adoptaweb.services.mail;


import co.lunadev.adoptaweb.services.mail.account.AccountApprovedEmail;
import co.lunadev.adoptaweb.services.mail.account.RegistrationReceivedEmail;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DispatcherEmail {

    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;
    private final TaskExecutor executor;

    public DispatcherEmail(JavaMailSender mailSender, Configuration freemarkerConfig, @Qualifier("emailExecutor") TaskExecutor executor) {
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.executor = executor;
    }

    public RegistrationReceivedEmail registrationReceivedEmail(){
        return new RegistrationReceivedEmail(this.mailSender,
               this.freemarkerConfig,this.executor);
    }

    public AccountApprovedEmail accountApprovedEmail(){
        return new AccountApprovedEmail(this.mailSender,
                this.freemarkerConfig,this.executor);
    }

}
