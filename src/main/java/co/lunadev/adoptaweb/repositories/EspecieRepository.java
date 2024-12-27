package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Short> {
}