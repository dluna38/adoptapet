package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsAnimalById(Long animalId);

    //TODO add pageable

    List<AnimalPublicInfo> findAllByHabilitadoAdopcion(boolean habilitadoAdopcion);
}
