package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.repositories.RazaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaService {
    private final RazaRepository razaRepository;

    public RazaService(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }
    @Cacheable(value = "cacheForLong",key = "'razaDe-'+#especieId")
    public List<Raza> getRazasByEspecieId(Short especieId) {
        return razaRepository.findByEspecie_Id(especieId);
    }
}
