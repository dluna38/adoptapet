package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.dto.RefugioPublicDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RefugioPublicMapper {
    Refugio toEntity(RefugioPublicDto refugioPublicDto);

    RefugioPublicDto toDto(Refugio refugio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Refugio partialUpdate(RefugioPublicDto refugioPublicDto, @MappingTarget Refugio refugio);
}
