package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Byte> {
}