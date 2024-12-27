package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.Municipio;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public List<Municipio> findAll(Long byDepartamentoId) {
        if(byDepartamentoId != null) {
            return municipioRepository.findAllByDepartamentoId(byDepartamentoId);
        }
        return municipioRepository.findAll();
    }
}
