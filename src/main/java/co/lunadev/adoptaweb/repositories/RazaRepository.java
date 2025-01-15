package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {

    @Query("select r from Raza r join r.especie e where e.id = :id")
    List<Raza> findByEspecie_Id(Short id);
}
