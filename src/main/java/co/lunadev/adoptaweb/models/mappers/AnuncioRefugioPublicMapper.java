package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.AnuncioRefugioPublicDto;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.dto.RefugioPublicDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnuncioRefugioPublicMapper {
    AnuncioRefugio toEntity(AnuncioRefugioPublicDto anuncioRefugioPublicDto);

    AnuncioRefugioPublicDto toDto(AnuncioRefugio anuncioRefugio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AnuncioRefugio partialUpdate(AnuncioRefugioPublicDto anuncioRefugioPublicDto, @MappingTarget AnuncioRefugio anuncioRefugio);

    RefugioPublicDto toDto(Refugio refugio);
}