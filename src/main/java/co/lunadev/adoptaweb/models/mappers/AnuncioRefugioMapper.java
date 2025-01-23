package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.dto.NewAnuncioRefugioDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnuncioRefugioMapper {
    AnuncioRefugio toEntity(NewAnuncioRefugioDto newAnuncioRefugioDto);

    NewAnuncioRefugioDto toDto(AnuncioRefugio anuncioRefugio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AnuncioRefugio partialUpdate(NewAnuncioRefugioDto newAnuncioRefugioDto, @MappingTarget AnuncioRefugio anuncioRefugio);
}