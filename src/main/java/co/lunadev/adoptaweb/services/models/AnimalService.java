package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.models.mappers.AnimalMapper;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.HistoriaClinicaRepository;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
import co.lunadev.adoptaweb.utils.UtilFile;
import co.lunadev.adoptaweb.utils.UtilSecurity;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Log
public class AnimalService {
    private final AnimalMapper animalMapper;
    AnimalRepository animalRepository;
    HistoriaClinicaRepository historiaClinicaRepository;

    public AnimalService(HistoriaClinicaRepository historiaClinicaRepository, AnimalRepository animalRepository,
                         AnimalMapper animalMapper) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
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

    public void update(Long animalId,AnimalUpdateDto requestAnimal, User user) {
        Animal originalAnimal = animalRepository.findById(animalId).orElseThrow(ResourceNotFoundException::new);

        UtilSecurity.checkEqual(originalAnimal.getRefugio().getId(),user.getRefugio().getId());

        animalMapper.partialUpdate(requestAnimal,originalAnimal);

        animalRepository.save(originalAnimal);
    }
    @Transactional
    public void delete(Long animalId) {
        Animal originalAnimal = animalRepository.findAnimalByIdFull(animalId).orElseThrow(ResourceNotFoundException::new);
        UtilSecurity.checkEqual(originalAnimal.getRefugio().getId(), Objects.requireNonNull(UtilSecurity.getUser()).getRefugio().getId());
        try {
            animalRepository.delete(originalAnimal);
            UtilFile.deleteFiles(originalAnimal.getFotos());
        } catch (Exception e) {
            throw new UnknownException("Ocurrió un error al eliminar el animal");
        }
    }

    public Page<Animal> findAllAnimalesAdoptablesByRefugioId(Long refugioId, Pageable pageable) {
        return animalRepository.findAll(AnimalSpecification.habilitadoAdopcion(true)
                .and(AnimalSpecification.filterByRefugioId(refugioId).and(AnimalSpecification.withRelations())),pageable);
    }
}
