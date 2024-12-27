package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.dto.RefugioPublicSimpleDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {MunicipioPublicMapper.class})
public interface RefugioPublicSimpleMapper {
    Refugio toEntity(RefugioPublicSimpleDto refugioPublicSimpleDto);

    RefugioPublicSimpleDto toDto(Refugio refugio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Refugio partialUpdate(RefugioPublicSimpleDto refugioPublicSimpleDto, @MappingTarget Refugio refugio);
}