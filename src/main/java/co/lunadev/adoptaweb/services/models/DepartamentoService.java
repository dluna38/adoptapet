package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.Departamento;
import co.lunadev.adoptaweb.repositories.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }
}
