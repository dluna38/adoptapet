package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.repositories.projections.RefugioInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Ref;
import java.util.Optional;

public interface RefugioRepository extends JpaRepository<Refugio, Long> {


    @EntityGraph(attributePaths = {"ubicacionMunicipio.departamento"})
    Optional<Refugio> findBySlugAndHabilitado(String slug, Boolean habilitado);
}
