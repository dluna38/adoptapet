package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Departamento;
import co.lunadev.adoptaweb.models.dto.DepartamentoPublicDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartamentoPublicMapper {
    DepartamentoPublicDto toDto(Departamento municipio);
}