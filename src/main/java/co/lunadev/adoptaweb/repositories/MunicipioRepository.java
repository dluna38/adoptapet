package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Municipio;
import co.lunadev.adoptaweb.repositories.projections.MunicipioPublicProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio,Short> {

    List<MunicipioPublicProjection> findAllByDepartamentoId(Long id);
    List<Municipio> findAllByDepartamentoId(Byte id);
}
