package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
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
    private EntityManager entityManager;

    public AnimalPublicController(AnimalRepository animalRepository, EntityManager entityManager) {
        this.animalRepository = animalRepository;
        this.entityManager = entityManager;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<Animal> getAnimalAdoptables(@RequestParam(required = false) Map<String, String> requestParams) {
        PageRequest pageable = PageRequest.of(0, 20);
        pageable.withSort(Sort.Direction.DESC,"createdAt");
        return new PageResponse<>(animalRepository.findAll(
                AnimalSpecification.animalSpecificationParamsPublicAllAnimal(requestParams)
                        .and(AnimalSpecification.withHabilitadoAdopcion(true)),
                pageable));
    }
}
