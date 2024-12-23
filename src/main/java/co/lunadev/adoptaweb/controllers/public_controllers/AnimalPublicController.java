package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.AnimalPublicDto;
import co.lunadev.adoptaweb.models.mappers.AnimalPublicMapper;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
import co.lunadev.adoptaweb.utils.UtilPage;
import jakarta.persistence.EntityManager;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public/animal")
public class AnimalPublicController {

    private final AnimalRepository animalRepository;
    private final AnimalPublicMapper animalPublicMapper;

    public AnimalPublicController(AnimalRepository animalRepository,
                                  AnimalPublicMapper animalPublicMapper) {
        this.animalRepository = animalRepository;
        this.animalPublicMapper = animalPublicMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnimalPublicDto> getAnimalAdoptables(@RequestParam(required = false) Map<String, String> requestParams) {
        PageRequest pageable = UtilPage.paramsToPageRequest(requestParams).withSort(Sort.Direction.DESC,"createdAt");
        return new PageResponse<>( animalRepository.findAll(
                AnimalSpecification.animalSpecificationParamsPublicAllAnimal(requestParams)
                        .and(AnimalSpecification.withHabilitadoAdopcion(true)),
                pageable).map(animalPublicMapper::toDto));
    }
}
