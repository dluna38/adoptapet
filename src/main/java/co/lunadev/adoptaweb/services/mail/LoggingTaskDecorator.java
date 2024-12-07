package co.lunadev.adoptaweb.services.mail;

import lombok.extern.java.Log;
import org.springframework.core.task.TaskDecorator;

@Log
public class LoggingTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        return ()->{
            log.info("TaskDecorator started");
            runnable.run();
            log.info("TaskDecorator finished");
        };
    }
}
