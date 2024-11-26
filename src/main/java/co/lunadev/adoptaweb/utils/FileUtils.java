package co.lunadev.adoptaweb.utils;

import co.lunadev.adoptaweb.models.archivos.BaseArchivos;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class FileUtils {

    private static final short maxSaveTrys= 2;
    private FileUtils() {
    }

    public static String getRandomName(){
        return UUID.randomUUID().toString();
    }

    public static String saveFile(String directoryPath, MultipartFile file){
        String fileOriginalName = file.getOriginalFilename();
        if(fileOriginalName == null || file.isEmpty()){
            return "";
        }
        int currentTry = 1;
        String savePath;
        //path+name+ext
        do {
            savePath = directoryPath + getRandomName() + getFileExtension(file.getOriginalFilename());
            currentTry++;
        } while (currentTry <= maxSaveTrys && Files.exists(Path.of(savePath)));

        if(currentTry >maxSaveTrys){
            return "";
        }
        try(FileOutputStream fOut = new FileOutputStream(savePath)) {
            fOut.write(file.getBytes());
            return savePath;
        } catch (IOException e) {
            log.severe("Fallo escribir archivo: "+e.getMessage());
        }
        return "";
    }
    public static File getFile(String directoryPath, String fileName){
        File file = new File(directoryPath+fileName);
        return file.exists() ? file : null;
    }
    public static HttpHeaders getHttpHeadersForImage(String fileName){
        HttpHeaders headers = new HttpHeaders();
        // inline | attachment
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        //to shown on browser, change content type
        String mediaType = parseImgExtToMediaType(fileName);
        if (mediaType.isEmpty()){
            return headers;
        }
        headers.add(HttpHeaders.CONTENT_TYPE, mediaType);
        return headers;
    }
    public static String parseImgExtToMediaType(String filename){
        String ext = getFileExtension(filename);
        if(ext.isEmpty()){
            return "";
        }
        if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg")){
            return MediaType.IMAGE_JPEG_VALUE;
        } else if (ext.equalsIgnoreCase("png")) {
            return MediaType.IMAGE_PNG_VALUE;
        }
        return "";
    }

    public static String getFileExtension(String filename){
        Pattern pattern = Pattern.compile("\\.(\\w+)$");
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static boolean fileIsImage(MultipartFile file){
        return !parseImgExtToMediaType(file.getOriginalFilename()).isEmpty();
    }
    public static String getFileNameFromPath(String path){
        int ultimoIndiceBarra = path.lastIndexOf('/');
        if (ultimoIndiceBarra != -1) {
            return path.substring(ultimoIndiceBarra + 1);
        } else {
            return path; // Si no hay barras, retorna el path completo
        }
    }
    public static int deleteFiles(List<? extends BaseArchivos> archivos){
        int totalDeleteFiles = 0;
        for(BaseArchivos file : archivos){
            try {
                if(file.getPath() == null){
                    continue;
                }
                Files.delete(Path.of(file.getPath()));
                totalDeleteFiles++;
            } catch (IOException e) {
               log.severe("No se pudo eliminar el archivo, porque: "+e.getMessage());
            }
        }
        return totalDeleteFiles;
    }
}
