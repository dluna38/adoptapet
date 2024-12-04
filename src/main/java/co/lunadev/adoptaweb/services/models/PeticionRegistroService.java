package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.dto_requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.PeticionRegistro;
import co.lunadev.adoptaweb.models.archivos.FotoPeticionRegistro;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import co.lunadev.adoptaweb.repositories.PeticionRegistroRepository;
import co.lunadev.adoptaweb.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeticionRegistroService {
    private PeticionRegistroRepository peticionRegistroRepository;
    private MunicipioRepository municipioRepository;

    public PeticionRegistroService(PeticionRegistroRepository peticionRegistroRepository, MunicipioRepository municipioRepository) {
        this.peticionRegistroRepository = peticionRegistroRepository;
        this.municipioRepository = municipioRepository;
    }

    @Transactional
    public void saveNewPeticionRegistro(NewPeticionRegistroRequest request) {

        if(! municipioRepository.existsById(request.getUbicacionMunicipio().getId())){
           throw new ValidationException("municipio","el municipio no existe");
        }
        if(request.getFotos() == null){
            throw new FieldRequiredException("fotos");
        }
        PeticionRegistro newPeticionRegistro = new PeticionRegistro();
        List<FotoPeticionRegistro> fotos = new ArrayList<>();

        for (MultipartFile file : request.getFotos()) {
            if (!FileUtils.fileIsImage(file)) {
                throw new ValidationException("archivo", "no es una imagen");
            }
            String pathFileSaved = FileUtils.saveFile(FotoPeticionRegistro.DIRECTORY_PATH, file);
            if (pathFileSaved.isEmpty()) {
                throw new UnknownException("No se pudo procesar los archivos");
            }
            fotos.add(new FotoPeticionRegistro(pathFileSaved,FileUtils.getFileNameFromPath(pathFileSaved), file.getOriginalFilename(),newPeticionRegistro));
        }
        try {
            newPeticionRegistro.setFotos(fotos);
            newPeticionRegistro.parsePeticionRegistroRequest(request);
            peticionRegistroRepository.save(newPeticionRegistro);
        } catch (Exception e) {
            FileUtils.deleteFiles(fotos);
            throw new UnknownException("Ocurrió un error guardando el registro");
        }
    }


    public List<PeticionRegistro> getAllPeticionesRegistro(){
        List<PeticionRegistro> peticiones = peticionRegistroRepository.findAll();

        //System.out.println(peticiones.get(0).getCorreo());
        //System.out.println(peticiones.get(0).getFotos());
        return peticiones;
    }
}
