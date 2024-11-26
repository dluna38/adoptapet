package co.lunadev.adoptaweb.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@Log
public class DirectoriesInitializer implements InitializingBean {

    @Value("${directories_to_make}")
    private List<String> directories;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("directories detected: "+directories.size());

        for (String directoryPath : directories){
            File directorio = new File(directoryPath);

            if (!directorio.exists()) {
                try {
                    Files.createDirectories(directorio.toPath());
                    log.info("Directorio creado correctamente: " + directoryPath);
                } catch (IOException e) {
                    throw new IOException("No se pudo crear el directorio: "+ directoryPath);
                }
            }
        }
    }
}
