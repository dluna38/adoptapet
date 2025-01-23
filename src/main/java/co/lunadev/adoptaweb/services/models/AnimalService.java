package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.controllers.dto_requests.UpdateNewAnimalRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.archivos.BaseArchivo;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.models.dto.AnimalRawDto;
import co.lunadev.adoptaweb.models.mappers.AnimalMapper;
import co.lunadev.adoptaweb.models.mappers.AnimalRawMapper;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.FotoAnimalRepository;
import co.lunadev.adoptaweb.repositories.HistoriaClinicaRepository;
import co.lunadev.adoptaweb.repositories.custom.AnimalCustomRepository;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
import co.lunadev.adoptaweb.utils.UtilFile;
import co.lunadev.adoptaweb.utils.UtilSecurity;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;


@Service
@Log
public class AnimalService {
    private final AnimalRawMapper animalRawMapper;
    private final AnimalMapper animalMapper;
    private final AnimalRepository animalRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final AnimalCustomRepository animalCustomRepository;
    private final FotoAnimalRepository fotoAnimalRepository;

    public AnimalService(HistoriaClinicaRepository historiaClinicaRepository, AnimalRepository animalRepository,
                         AnimalMapper animalMapper,
                         AnimalRawMapper animalRawMapper, AnimalCustomRepository animalCustomRepository, FotoAnimalRepository fotoAnimalRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
        this.animalRawMapper = animalRawMapper;
        this.animalCustomRepository = animalCustomRepository;
        this.fotoAnimalRepository = fotoAnimalRepository;
    }

    @Transactional
    public void newAnimal(@Valid NewAnimalRequest animalRequest, User user) {
        System.out.println(animalRequest);
        if (animalRequest.getFotos() == null) {
            throw new FieldRequiredException("fotos");
        }
        if (animalRequest.getFotos().size() > FotoAnimal.MAX_FILES) {
            throw new ValidationException("No mas de " + FotoAnimal.MAX_FILES + " fotos");
        }

        Animal newAnimal = animalRequest.getAnimal();
        HistoriaClinica historiaClinica = animalRequest.getHistoriaClinica();

        if (historiaClinica.getObservaciones() != null && historiaClinica.getObservaciones().isBlank()) {
            historiaClinica.setObservaciones(null);
        }
        if (historiaClinica.getVacunas() != null && historiaClinica.getVacunas().isBlank()) {
            historiaClinica.setVacunas(null);
        }
        if (newAnimal.getChipCode() != null && newAnimal.getChipCode().isBlank()) {
            newAnimal.setChipCode(null);
        }
        try {
            newAnimal.setHistoriaClinica(historiaClinica);
            newAnimal.setRefugio(user.getRefugio());
            newAnimal.setFotos(UtilFile.saveFilesFromRequest(animalRequest.getFotos(), FotoAnimal.DIRECTORY_PATH)
                    .stream().map(bFile ->
                            new FotoAnimal(bFile.getPath(), bFile.getNombreInterno(), bFile.getNombreOriginalFoto(), newAnimal))
                    .toList());
            animalRepository.save(newAnimal);
            animalRepository.updateFotoPortadaById(newAnimal.getFotos().get(0), newAnimal.getId());
        } catch (Exception e) {
            log.severe("Error al guardar animal: " + e.getMessage());
            e.printStackTrace();
            UtilFile.deleteFiles(newAnimal.getFotos());
            throw new UnknownException("Ocurrio un error al crear el animal");
        }


        //animalRepository.save(animal);
    }

    @Transactional
    public void updateWithNewAnimal(Long animalId, UpdateNewAnimalRequest animalRequest, User user) {
        Animal originalAnimal = animalCustomRepository
                .findCustomById(AnimalSpecification.withRelations(false).and(AnimalSpecification.whereId(animalId)), animalId)
                .orElseThrow(ResourceNotFoundException::new);

        UtilSecurity.checkEqual(originalAnimal.getRefugio().getId(), user.getRefugio().getId());

        if (animalRequest.getFotos() != null && animalRequest.getFotos().size() > FotoAnimal.MAX_FILES) {
            throw new ValidationException("No mas de " + FotoAnimal.MAX_FILES + " fotos");
        }

        Animal newAnimal = animalRequest.getAnimal();
        HistoriaClinica historiaClinica = animalRequest.getHistoriaClinica();
        newAnimal.setId(originalAnimal.getId());
        historiaClinica.setId(originalAnimal.getHistoriaClinica().getId());

        if (historiaClinica.getObservaciones() != null && historiaClinica.getObservaciones().isBlank()) {
            historiaClinica.setObservaciones(null);
        }
        if (historiaClinica.getVacunas() != null && historiaClinica.getVacunas().isBlank()) {
            historiaClinica.setVacunas(null);
        }
        if (newAnimal.getChipCode() != null && newAnimal.getChipCode().isBlank()) {
            newAnimal.setChipCode(null);
        }

        try {
            newAnimal.setHistoriaClinica(historiaClinica);
            newAnimal.setRefugio(user.getRefugio());
            System.out.println(originalAnimal);
            System.out.println(animalRequest);
            System.out.println(newAnimal);
            //System.out.println(newAnimal);
            if (animalRequest.getFotos() == null) {
                newAnimal.setFotoPortada(originalAnimal.getFotoPortada());
            } else {
                BaseArchivo bFile = replaceFotoPortada(originalAnimal.getFotoPortada(), animalRequest.getFotos().get(0));
                FotoAnimal newFotoPortada = new FotoAnimal(bFile.getPath(), bFile.getNombreInterno(), bFile.getNombreOriginalFoto(), newAnimal);
                fotoAnimalRepository.save(newFotoPortada);
                newAnimal.setFotoPortada(newFotoPortada);
            }
            animalRepository.save(newAnimal);
        }catch (Exception e) {
            log.severe("Error al guardar animal: " + e.getMessage());
            e.printStackTrace();
            UtilFile.deleteFiles(newAnimal.getFotos());
            UtilFile.deleteFiles(List.of(newAnimal.getFotoPortada()));
            throw new UnknownException("Ocurrio un error al crear el animal");
        }
    }

    public void update(Long animalId, AnimalUpdateDto requestAnimal, User user) {
        Animal originalAnimal = animalRepository.findById(animalId).orElseThrow(ResourceNotFoundException::new);

        UtilSecurity.checkEqual(originalAnimal.getRefugio().getId(), user.getRefugio().getId());

        animalMapper.partialUpdate(requestAnimal, originalAnimal);

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
                .and(AnimalSpecification.filterByRefugioId(refugioId).and(AnimalSpecification.withRelations(true))), pageable);
    }

    public Page<Animal> findAll(Specification<Animal> specs, Pageable pageable) {
        return animalRepository.findAll(specs, pageable);
    }

    public AnimalRawDto getDetailAnimal(Long idAnimal, User user) {
        System.out.println("-----------------");
        Animal animal = animalCustomRepository
                .findCustomById(AnimalSpecification.withRelations(false).and(AnimalSpecification.whereId(idAnimal)), idAnimal)
                .orElseThrow(ResourceNotFoundException::new);
        UtilSecurity.checkEqual(animal.getRefugio().getId(), user.getRefugio().getId());
        return animalRawMapper.toDto(animal);
    }

    private BaseArchivo replaceFotoPortada(FotoAnimal oldFoto, MultipartFile newFoto) {
        if (oldFoto != null) {
            UtilFile.deleteFile(oldFoto);
            fotoAnimalRepository.deleteById(oldFoto.getId());
        }
        return UtilFile.saveFilesFromRequest(newFoto, FotoAnimal.DIRECTORY_PATH);
    }
}
