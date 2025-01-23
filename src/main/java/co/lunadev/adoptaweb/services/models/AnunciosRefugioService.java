package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.AnuncioRefugioPublicDto;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.dto.NewAnuncioRefugioDto;
import co.lunadev.adoptaweb.models.mappers.AnuncioRefugioMapper;
import co.lunadev.adoptaweb.models.mappers.AnuncioRefugioPublicMapper;
import co.lunadev.adoptaweb.repositories.AnunciosRefugioRepository;
import co.lunadev.adoptaweb.utils.UtilSecurity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
public class AnunciosRefugioService {

    private final AnunciosRefugioRepository anunciosRefugioRepository;
    private final AnuncioRefugioMapper anuncioRefugioMapper;
    private final AnuncioRefugioPublicMapper anuncioRefugioPublicMapper;

    public AnunciosRefugioService(AnunciosRefugioRepository anunciosRefugioRepository,
                                  AnuncioRefugioMapper anuncioRefugioMapper,
                                  AnuncioRefugioPublicMapper anuncioRefugioPublicMapper) {
        this.anunciosRefugioRepository = anunciosRefugioRepository;
        this.anuncioRefugioMapper = anuncioRefugioMapper;
        this.anuncioRefugioPublicMapper = anuncioRefugioPublicMapper;
    }

    public Page<AnuncioRefugio> findAllByRefugioIdPublicado(Long refugioId,Boolean publicado, Pageable pageable) {
        return anunciosRefugioRepository.findByRefugio_IdAndPublicado(refugioId,publicado,pageable);
    }
    @Transactional
    public void create(@Valid NewAnuncioRefugioDto newAnuncioDto, User user) {
        AnuncioRefugio anuncioRefugio = anuncioRefugioMapper.toEntity(newAnuncioDto);
        anuncioRefugio.setRefugio(user.getRefugio());
        anunciosRefugioRepository.save(anuncioRefugio);
    }

    public void delete(Long anuncioId) {
        AnuncioRefugio anuncioDb = anunciosRefugioRepository.findById(anuncioId).orElseThrow(ResourceNotFoundException::new);
        UtilSecurity.checkEqual(anuncioDb.getRefugio().getId(), Objects.requireNonNull(UtilSecurity.getUser()).getRefugio().getId());
        anunciosRefugioRepository.delete(anuncioDb);
    }

    public Page<AnuncioRefugioPublicDto> findBySlug(String slug, Pageable pageable) {
        return anunciosRefugioRepository.findAllByRefugio_Slug(slug, pageable).map(anuncioRefugioPublicMapper::toDto);
    }

    public void edit(Long anuncioId, @Valid NewAnuncioRefugioDto newAnuncioDto) {
        AnuncioRefugio anuncioDb = anunciosRefugioRepository.findById(anuncioId).orElseThrow(ResourceNotFoundException::new);
        UtilSecurity.checkEqual(anuncioDb.getRefugio().getId(), Objects.requireNonNull(UtilSecurity.getUser()).getRefugio().getId());

        anuncioDb.setTitulo(newAnuncioDto.getTitulo());
        anuncioDb.setContenido(newAnuncioDto.getContenido());
        anuncioDb.setPublicado(newAnuncioDto.getPublicado());

        anunciosRefugioRepository.save(anuncioDb);
    }
}
