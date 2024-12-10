package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.services.models.AnimalService;
import co.lunadev.adoptaweb.validators.NewAnimalValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
        animal.setFotos(fotos);
        animalService.newAnimal(animal,user);
    }
    @PutMapping("/{animalId}")
    public void editAnimal(@PathVariable Long animalId,@Valid @RequestBody NewAnimalRequest animal){
        animalService.update(animalId,animal);
    }
    @DeleteMapping("/{animalId}")
    public void deleteAnimal(@PathVariable int animalId){

    }
    @GetMapping
    public void allAnimal(){

    }

    @InitBinder
    public void initBinder(DataBinder binder){
        binder.addValidators(newAnimalValidator);
    }
}
