package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.dto.AnimalDto;
import co.lunadev.adoptaweb.models.dto.AnimalPublicDto;
import co.lunadev.adoptaweb.models.mappers.AnimalPrivateMapper;
import co.lunadev.adoptaweb.repositories.specifications.AnimalSpecification;
import co.lunadev.adoptaweb.services.models.AnimalService;
import co.lunadev.adoptaweb.utils.UtilPage;
import co.lunadev.adoptaweb.validators.NewAnimalValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    private final NewAnimalValidator newAnimalValidator;
    private final AnimalPrivateMapper animalPrivateMapper;

    public AnimalController(AnimalService animalService, NewAnimalValidator newAnimalValidator,
                            AnimalPrivateMapper animalPrivateMapper) {
        this.animalService = animalService;
        this.newAnimalValidator = newAnimalValidator;
        this.animalPrivateMapper = animalPrivateMapper;
    }
    @GetMapping("/{idAnimal}")
    public void getInfoAnimal(@PathVariable Long idAnimal) {
        animalService.getDetailAnimal(idAnimal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newAnimal(@Valid @RequestPart(name = "datos") NewAnimalRequest animal,
                          @RequestPart(name = "fotos",required = false) List<MultipartFile> fotos,@AuthenticationPrincipal User user) {
        newAnimalValidator.validateObject(animal);
        animal.setFotos(fotos);
        animalService.newAnimal(animal,user);
    }
    @PutMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editAnimal(@PathVariable Long animalId, @Valid @RequestBody AnimalUpdateDto animal, @AuthenticationPrincipal User user) {
        animalService.update(animalId,animal,user);
    }
    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable Long animalId){
        animalService.delete(animalId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnimalDto> getAnimalAllAnimals(@RequestParam(required = false) Map<String, String> requestParams,@AuthenticationPrincipal User user) {
        PageRequest pageable = UtilPage.paramsToPageRequest(requestParams).withSort(Sort.Direction.DESC,"createdAt");
        return new PageResponse<>(animalService.findAll(AnimalSpecification.animalSpecificationParamsPublicAllAnimal(requestParams)
                .and(AnimalSpecification.withRelations()
                        .and(AnimalSpecification.filterByRefugioId(user.getRefugio().getId()))), pageable).map(animalPrivateMapper::toDto));
    }

}
