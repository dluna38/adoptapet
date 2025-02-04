package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.dto.AnimalPublicDto;
import co.lunadev.adoptaweb.models.mappers.AnimalPublicMapper;
import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.custom.AnimalCustomRepository;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
import co.lunadev.adoptaweb.utils.UtilPage;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public/animal")
public class AnimalPublicController {

    private final AnimalRepository animalRepository;
    private final AnimalCustomRepository animalCustomRepository;
    private final AnimalPublicMapper animalPublicMapper;

    public AnimalPublicController(AnimalRepository animalRepository, AnimalCustomRepository animalCustomRepository,
                                  AnimalPublicMapper animalPublicMapper) {
        this.animalRepository = animalRepository;
        this.animalCustomRepository = animalCustomRepository;
        this.animalPublicMapper = animalPublicMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnimalPublicDto> getAnimalAdoptables(@RequestParam(required = false) Map<String, String> requestParams) {
        PageRequest pageable = UtilPage.paramsToPageRequest(requestParams).withSort(Sort.Direction.DESC,"createdAt");
        //animalRepository.findAllWithHistoriaClinica(true, pageable);
        return new PageResponse<>(animalCustomRepository.findAllCustom(
                AnimalSpecification.animalSpecificationParamsPublicAllAnimal(requestParams)
                .and(AnimalSpecification.habilitadoAdopcion(true)
                        .and(AnimalSpecification.withRelations(true))),pageable).map(animalPublicMapper::toDto));
    }

}
