package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.FotoAnimalRepository;
import co.lunadev.adoptaweb.utils.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/img")
public class ImageController {

    FotoAnimalRepository fotoAnimalRepository;

    public ImageController(FotoAnimalRepository fotoAnimalRepository) {
        this.fotoAnimalRepository = fotoAnimalRepository;
    }

    @GetMapping("/animal/{fileName}")
    public ResponseEntity<FileSystemResource> getImgAnimal(@PathVariable String fileName) {
        FotoAnimal fotoAnimal= fotoAnimalRepository.findFotoAnimalByNombreInterno(fileName).orElseThrow(ResourceNotFoundException::new);

        File file = new File(fotoAnimal.getPath());

        if(file.exists()){
            return ResponseEntity.ok()
                    .headers(FileUtils.getHttpHeadersForImage(fotoAnimal.getNombreOriginalFoto(), FileUtils.ContentTypeFile.INLINE))
                    .body(new FileSystemResource(file));
        }else {
            throw new ResourceNotFoundException("File not found");
        }
        /*Map<String,String> myMap = new HashMap<>();
        myMap.put("filename",fileName);
        myMap.put("path",pathFiles+fileName);
        myMap.put("file1-Exist",Boolean.toString(file.exists()));

        return ResponseEntity.ok(myMap);*/
    }
}
