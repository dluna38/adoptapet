package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoAnimalRepository extends JpaRepository<FotoAnimal, Long> {

  Optional<FotoAnimal> findFotoAnimalByNombreInterno(String nombreInterno);

}