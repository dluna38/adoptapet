package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.response.DepYMunWithIdDto;
import co.lunadev.adoptaweb.models.mappers.MunicipioPublicWithIdMapper;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import co.lunadev.adoptaweb.repositories.projections.MunicipioPublicProjection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final MunicipioPublicWithIdMapper municipioPublicWithIdMapper;

    public MunicipioService(MunicipioRepository municipioRepository,
                            MunicipioPublicWithIdMapper municipioPublicWithIdMapper) {
        this.municipioRepository = municipioRepository;
        this.municipioPublicWithIdMapper = municipioPublicWithIdMapper;
    }

    @Cacheable(value = "cacheForLong",key = "'municipiosDe-'+#byDepartamentoId")
    public DepYMunWithIdDto findAllPublic(Byte byDepartamentoId) {
        return new DepYMunWithIdDto(byDepartamentoId,
                municipioRepository.findAllByDepartamentoId(byDepartamentoId).
                        stream().map(municipioPublicWithIdMapper::toDto).toList());
    }

}
