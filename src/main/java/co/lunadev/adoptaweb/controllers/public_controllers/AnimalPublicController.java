package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.repositories.AnimalRepository;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    public List<AnimalPublicInfo> getAnimalAdoptables() {
        return animalRepository.findAllByHabilitadoAdopcion(true);
    }
}
