package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.AnuncioRefugioPublicDto;
import co.lunadev.adoptaweb.models.dto.RefugioPublicSimpleDto;
import co.lunadev.adoptaweb.models.dto.AnimalPublicDto;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.dto.RefugioPublicDto;
import co.lunadev.adoptaweb.models.mappers.AnimalPublicMapper;
import co.lunadev.adoptaweb.models.mappers.AnuncioRefugioPublicMapper;
import co.lunadev.adoptaweb.models.mappers.RefugioPublicMapper;
import co.lunadev.adoptaweb.models.mappers.RefugioPublicSimpleMapper;
import co.lunadev.adoptaweb.repositories.RefugioRepository;
import co.lunadev.adoptaweb.repositories.specifications.RefugioSpecification;
import co.lunadev.adoptaweb.utils.UtilPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RefugioService {

    private final RefugioRepository refugioRepository;
    private final AnimalService animalService;
    private final AnimalPublicMapper animalPublicMapper;
    private final AnunciosRefugioService anunciosRefugioService;
    private final AnuncioRefugioPublicMapper anuncioRefugioPublicMapper;
    private final RefugioPublicMapper refugioPublicMapper;
    private final RefugioPublicSimpleMapper refugioPublicSimpleMapper;

    public RefugioService(RefugioRepository refugioRepository, AnimalService animalService,
                          AnimalPublicMapper animalPublicMapper, AnunciosRefugioService anunciosRefugioService,
                          AnuncioRefugioPublicMapper anuncioRefugioPublicMapper,
                          RefugioPublicMapper refugioPublicMapper,
                          RefugioPublicSimpleMapper refugioPublicSimpleMapper) {
        this.refugioRepository = refugioRepository;
        this.animalService = animalService;
        this.animalPublicMapper = animalPublicMapper;
        this.anunciosRefugioService = anunciosRefugioService;
        this.anuncioRefugioPublicMapper = anuncioRefugioPublicMapper;
        this.refugioPublicMapper = refugioPublicMapper;
        this.refugioPublicSimpleMapper = refugioPublicSimpleMapper;
    }
    @Transactional(readOnly = true)
    public RefugioPublicDto findRefugioBySlug(String slug) {
        Refugio refugio = refugioRepository.findBySlugAndHabilitado(slug,true).orElseThrow(ResourceNotFoundException::new);

        List <AnimalPublicDto> animales = animalService.findAllAnimalesAdoptablesByRefugioId(refugio.getId(),
                PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdAt"))).map(animalPublicMapper::toDto).toList();


        System.out.println(animales.get(0).getRefugio());

        List<AnuncioRefugioPublicDto> anuncios = anunciosRefugioService.findAllByRefugioId(
                refugio.getId(), PageRequest.of(0, 6,
                        Sort.by(Sort.Direction.DESC, "createdAt"))).map(anuncioRefugioPublicMapper::toDto).toList();

        RefugioPublicDto refugioPublicDto = refugioPublicMapper.toDto(refugio);
        refugioPublicDto.setAnimales(animales);
        refugioPublicDto.setAnuncios(anuncios);
        return refugioPublicDto;
    }

    public PageResponse<RefugioPublicSimpleDto> findAll(Map<String, String> requestParams) {
        PageRequest pageable = UtilPage.paramsToPageRequest(requestParams).withSort(Sort.Direction.DESC,"nombreRefugio");

        return new PageResponse<>(refugioRepository.findAll(RefugioSpecification.refugioPublicParamsSpecification(requestParams).
                and(RefugioSpecification.refugioHabilitado(true))
                .and(RefugioSpecification.withMunicipioAndDepartamento()), pageable).map(refugioPublicSimpleMapper::toDto));
    }
}
