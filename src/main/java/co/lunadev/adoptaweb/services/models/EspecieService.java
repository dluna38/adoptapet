package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.Especie;
import co.lunadev.adoptaweb.repositories.EspecieRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }
    @Cacheable(value = "cacheForLong",key = "'especies'")
    public List<Especie> getAllEspecies() {
        return especieRepository.findAll();
    }
}
