package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/animal")
public class AnimalPublicController {

    private final AnimalRepository animalRepository;

    public AnimalPublicController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalPublicInfo> getAnimalAdoptables() {
        return animalRepository.findAllByHabilitadoAdopcion(true);
    }
}
