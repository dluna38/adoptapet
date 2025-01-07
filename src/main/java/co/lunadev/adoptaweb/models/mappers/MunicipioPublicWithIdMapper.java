package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Municipio;
import co.lunadev.adoptaweb.models.MunicipioPublicWithIdDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MunicipioPublicWithIdMapper {
    Municipio toEntity(MunicipioPublicWithIdDto municipioPublicWithIdDto);

    MunicipioPublicWithIdDto toDto(Municipio municipio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Municipio partialUpdate(MunicipioPublicWithIdDto municipioPublicWithIdDto, @MappingTarget Municipio municipio);
}