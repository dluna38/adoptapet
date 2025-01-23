package co.lunadev.adoptaweb.models;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnuncioRefugioPrivateDtoMapper {
    AnuncioRefugio toEntity(AnuncioRefugioPrivateDto anuncioRefugioPrivateDto);

    AnuncioRefugioPrivateDto toDto(AnuncioRefugio anuncioRefugio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AnuncioRefugio partialUpdate(AnuncioRefugioPrivateDto anuncioRefugioPrivateDto, @MappingTarget AnuncioRefugio anuncioRefugio);
}