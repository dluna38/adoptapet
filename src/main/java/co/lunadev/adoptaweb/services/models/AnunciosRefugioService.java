package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.repositories.AnunciosRefugioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AnunciosRefugioService {

    private final AnunciosRefugioRepository anunciosRefugioRepository;

    public AnunciosRefugioService(AnunciosRefugioRepository anunciosRefugioRepository) {
        this.anunciosRefugioRepository = anunciosRefugioRepository;
    }

    public Page<AnuncioRefugio> findAllByRefugioId(Long refugioId,Pageable pageable) {
        return anunciosRefugioRepository.findAllByRefugioId(refugioId,pageable);
    }
}
