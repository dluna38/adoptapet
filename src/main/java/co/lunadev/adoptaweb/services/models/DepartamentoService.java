package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.Departamento;
import co.lunadev.adoptaweb.repositories.DepartamentoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }
    @Cacheable(value = "cacheForLong",key = "'departamentos'")
    public List<Departamento> findAllDepartamentos() {
        return departamentoRepository.findAll();
    }
}
