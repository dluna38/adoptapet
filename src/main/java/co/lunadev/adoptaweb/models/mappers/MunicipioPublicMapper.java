package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Municipio;
import co.lunadev.adoptaweb.models.dto.MunicipioPublicDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MunicipioPublicMapper {
    MunicipioPublicDto toDto(Municipio municipio);

    Municipio toEntity(MunicipioPublicDto municipioPublicDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Municipio partialUpdate(MunicipioPublicDto municipioPublicDto, @MappingTarget Municipio municipio);
}