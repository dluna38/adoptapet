package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.HistoriaClinicaRepository;
import co.lunadev.adoptaweb.utils.UtilFile;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Log
public class AnimalService {

    AnimalRepository animalRepository;
    HistoriaClinicaRepository historiaClinicaRepository;

    public AnimalService(HistoriaClinicaRepository historiaClinicaRepository, AnimalRepository animalRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.animalRepository = animalRepository;
    }
    @Transactional
    public void newAnimal(@Valid NewAnimalRequest animalRequest, User user) {
        System.out.println(animalRequest);
        if(animalRequest.getFotos() == null){
            throw new FieldRequiredException("fotos");
        }
        if(animalRequest.getFotos().size() > FotoAnimal.MAX_FILES){
            throw new ValidationException("No mas de "+ FotoAnimal.MAX_FILES+" fotos");
        }

        Animal newAnimal = animalRequest.getAnimal();
        HistoriaClinica historiaClinica = animalRequest.getHistoriaClinica();

        if(historiaClinica.getObservaciones() != null && historiaClinica.getObservaciones().isBlank()){
            historiaClinica.setObservaciones(null);
        }
        if(historiaClinica.getVacunas() != null && historiaClinica.getVacunas().isBlank()){
            historiaClinica.setVacunas(null);
        }
        if(newAnimal.getChipCode() != null && newAnimal.getChipCode().isBlank()){
            newAnimal.setChipCode(null);
        }
        try {
            //newAnimal.setRefugio(new Refugio(user.getRefugio));
            historiaClinicaRepository.save(historiaClinica);
            newAnimal.setHistoriaClinica(historiaClinica);
            newAnimal.setRefugio(user.getRefugio());
            newAnimal.setFotos(UtilFile.saveFilesFromRequest(animalRequest.getFotos(), FotoAnimal.DIRECTORY_PATH)
                    .stream().map(bFile ->
                            new FotoAnimal(bFile.getPath(),bFile.getNombreInterno(),bFile.getNombreOriginalFoto(),newAnimal))
                    .toList());
            animalRepository.save(newAnimal);
            animalRepository.updateFotoPortadaById(newAnimal.getFotos().get(0), newAnimal.getId());
        } catch (Exception e) {
            log.severe("Error al guardar animal: "+e.getMessage());
            e.printStackTrace();
            UtilFile.deleteFiles(newAnimal.getFotos());
            throw new UnknownException("Ocurrio un error al crear el animal");
        }


        //animalRepository.save(animal);
    }

    public void update(Long animalId, @Valid NewAnimalRequest updatedAnimal) {
        Animal originalAnimal = animalRepository.findById(animalId).orElseThrow(ResourceNotFoundException::new);


        animalRepository.save(originalAnimal);
    }
}
