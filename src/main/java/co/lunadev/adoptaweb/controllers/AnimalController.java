package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.services.models.AnimalService;
import co.lunadev.adoptaweb.validators.NewAnimalValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    private final NewAnimalValidator newAnimalValidator;

    public AnimalController(AnimalService animalService, NewAnimalValidator newAnimalValidator) {
        this.animalService = animalService;
        this.newAnimalValidator = newAnimalValidator;
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
    public void allAnimal(){

    }

}
