package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.dto_requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.PeticionRegistro;
import co.lunadev.adoptaweb.models.archivos.FotoPeticionRegistro;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import co.lunadev.adoptaweb.repositories.PeticionRegistroRepository;
import co.lunadev.adoptaweb.services.mail.DispatcherEmail;
import co.lunadev.adoptaweb.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeticionRegistroService {
    private final PeticionRegistroRepository peticionRegistroRepository;
    private final MunicipioRepository municipioRepository;
    private final DispatcherEmail dispatcherEmail;

    public PeticionRegistroService(PeticionRegistroRepository peticionRegistroRepository, MunicipioRepository municipioRepository, DispatcherEmail dispatcherEmail) {
        this.peticionRegistroRepository = peticionRegistroRepository;
        this.municipioRepository = municipioRepository;
        this.dispatcherEmail = dispatcherEmail;
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

        List<FotoPeticionRegistro> fotos = FileUtils.saveFilesFromRequest(request.getFotos(),FotoPeticionRegistro.DIRECTORY_PATH)
                .stream().map(bFile ->
                        new FotoPeticionRegistro(bFile.getPath(),bFile.getNombreInterno(),bFile.getNombreOriginalFoto(),newPeticionRegistro))
                .toList();

        try {
            newPeticionRegistro.setFotos(fotos);
            newPeticionRegistro.parsePeticionRegistroRequest(request);
            peticionRegistroRepository.save(newPeticionRegistro);
            dispatcherEmail.registrationReceivedEmail().body(newPeticionRegistro.getCorreo(), newPeticionRegistro.getNombreRefugio()).execute();
        } catch (Exception e) {
            FileUtils.deleteFiles(fotos);
            throw new UnknownException("Ocurrió un error guardando el registro");
        }
    }


    public List<PeticionRegistro> getAllPeticionesRegistro(){
        //List<PeticionRegistro> peticiones = peticionRegistroRepository.findAll();

        //System.out.println(peticiones.get(0).getCorreo());
        //System.out.println(peticiones.get(0).getFotos());
        return peticionRegistroRepository.findAll();
    }
}
