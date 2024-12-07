package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {

}
