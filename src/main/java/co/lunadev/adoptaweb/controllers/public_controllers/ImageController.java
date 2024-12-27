package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.FotoAnimalRepository;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import co.lunadev.adoptaweb.utils.UtilFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/public/img")
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
                    .headers(UtilFile.getHttpHeadersForImage(fotoAnimal.getNombreOriginalFoto(), UtilFile.ContentTypeFile.INLINE))
                    .body(new FileSystemResource(file));
        }else {
            throw new ResourceNotFoundException("File not found");
        }
    }
    @GetMapping("/animal/all/{idAnimal}")
    public ResponseEntity<List<AnimalPublicInfo.FotosAnimalInfo>> getAllImgsAnimal(@PathVariable Long idAnimal) {
        return ResponseEntity.ok(fotoAnimalRepository.findAllByAnimalId(idAnimal));
    }
}
