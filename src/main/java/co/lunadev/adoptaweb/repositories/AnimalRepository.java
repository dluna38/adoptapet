package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsAnimalById(Long animalId);

    //TODO add pageable

    List<AnimalPublicInfo> findAllByHabilitadoAdopcion(boolean habilitadoAdopcion);

    @Transactional
    @Modifying
    @Query("update Animal a set a.fotoPortada = ?1 where a.id = ?2")
    int updateFotoPortadaById(FotoAnimal fotoPortada, Long id);

}
