package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.response.RefugioPublicDto;
import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.AnimalPublicDto;
import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.mappers.AnimalPublicMapper;
import co.lunadev.adoptaweb.repositories.RefugioRepository;
import co.lunadev.adoptaweb.repositories.projections.RefugioInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefugioService {

    private final RefugioRepository refugioRepository;
    private final AnimalService animalService;
    private final AnimalPublicMapper animalPublicMapper;
    private final AnunciosRefugioService anunciosRefugioService;

    public RefugioService(RefugioRepository refugioRepository, AnimalService animalService,
                          AnimalPublicMapper animalPublicMapper, AnunciosRefugioService anunciosRefugioService) {
        this.refugioRepository = refugioRepository;
        this.animalService = animalService;
        this.animalPublicMapper = animalPublicMapper;
        this.anunciosRefugioService = anunciosRefugioService;
    }
    @Transactional(readOnly = true)
    public RefugioPublicDto findRefugioBySlug(String slug) {
        Refugio refugio = refugioRepository.findBySlugAndHabilitado(slug,true).orElseThrow(ResourceNotFoundException::new);
        List <AnimalPublicDto> animales = animalService.findAllAnimalesAdoptablesByRefugioId(refugio.getId(),
                PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdAt"))).map(animalPublicMapper::toDto).toList();
        refugio.setUsuario(null);

        List<AnuncioRefugio> anuncios = anunciosRefugioService.findAllByRefugioId(
                refugio.getId(),PageRequest.of(0, 6,
                        Sort.by(Sort.Direction.DESC, "createdAt"))).getContent();

        return new RefugioPublicDto(refugio,animales,anuncios);
    }
}
