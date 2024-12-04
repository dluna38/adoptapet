package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
