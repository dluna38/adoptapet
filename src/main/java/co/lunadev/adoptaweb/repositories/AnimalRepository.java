package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import co.lunadev.adoptaweb.repositories.projections.AnimalWithRefugio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    boolean existsAnimalById(Long animalId);

    //TODO add pageable
    //@Query("select a from Animal a join fetch a.fotoPortada join fetch a.historiaClinica join fetch a.refugio join fetch a.raza ar join fetch ar.especie")
    Page<Animal> findAll(Specification<Animal> spec, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Animal a set a.fotoPortada = ?1 where a.id = ?2")
    int updateFotoPortadaById(FotoAnimal fotoPortada, Long id);

    Optional<AnimalWithRefugio> findAnimalWithRefugioById(Long id);

    @Query(value = "select a from Animal a left join fetch a.fotos join fetch a.historiaClinica where a.id=:id")
    Optional<Animal> findAnimalByIdFull(Long id);
}
