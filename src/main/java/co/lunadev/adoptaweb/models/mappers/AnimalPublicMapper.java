package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.dto.AnimalPublicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,uses = {MunicipioPublicMapper.class, DepartamentoPublicMapper.class})
public interface AnimalPublicMapper {
    @Mapping(target = "refugio.municipio", source = "refugio.ubicacionMunicipio")
    AnimalPublicDto toDto(Animal animal);
}