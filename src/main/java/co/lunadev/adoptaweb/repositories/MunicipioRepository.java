package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio,Short> {

    List<Municipio> findAllByDepartamentoId(Long id);
}
