package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.repositories.projections.AnimalPublicInfo;
import co.lunadev.adoptaweb.repositories.projections.AnimalWithRefugio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    boolean existsAnimalById(Long animalId);

    //cant be use with specification
    //@Query("select a from Animal a join fetch a.fotoPortada join fetch a.historiaClinica join fetch a.refugio ref join fetch ref.ubicacionMunicipio mun join fetch mun.departamento join fetch a.raza ar join fetch ar.especie")
    //too many querys
    //@EntityGraph(attributePaths = {"raza.especie", "refugio.ubicacionMunicipio.departamento", "historiaClinica", "fotoPortada"})
    Page<Animal> findAll(Specification<Animal> spec, Pageable pageable);

    @Query("SELECT a FROM Animal a JOIN FETCH a.historiaClinica WHERE a.habilitadoAdopcion = :habilitado ORDER BY a.createdAt DESC")
    Page<Animal> findAllWithHistoriaClinica(@Param("habilitado") boolean habilitado, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Animal a set a.fotoPortada = ?1 where a.id = ?2")
    int updateFotoPortadaById(FotoAnimal fotoPortada, Long id);

    Optional<AnimalWithRefugio> findAnimalWithRefugioById(Long id);

    @Query(value = "select a from Animal a left join fetch a.fotos join fetch a.historiaClinica where a.id=:id")
    Optional<Animal> findAnimalByIdFull(Long id);
}
