package co.lunadev.adoptaweb.config;

import co.lunadev.adoptaweb.services.mail.DispatcherEmail;
import co.lunadev.adoptaweb.services.mail.LoggingTaskDecorator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.host}")
    private String mailHost;

    @Bean
    JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(587);
        mailSender.setDefaultEncoding(StandardCharsets.UTF_8.name());
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    DispatcherEmail dispatcherEmail(freemarker.template.Configuration configuration) {
        return new DispatcherEmail(mailSender(),configuration,emailExecutor());
    }

    @Bean
    ThreadPoolTaskExecutor emailExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5); //prom threads
        taskExecutor.setMaxPoolSize(10); //max threads when queue is full
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        //taskExecutor.setTaskDecorator(new LoggingTaskDecorator());
        return taskExecutor;
    }

}
