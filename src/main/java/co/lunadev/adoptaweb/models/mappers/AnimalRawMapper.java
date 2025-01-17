package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.dto.AnimalRawDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimalRawMapper {
    Animal toEntity(AnimalRawDto animalRawDto);

    AnimalRawDto toDto(Animal animal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Animal partialUpdate(AnimalRawDto animalRawDto, @MappingTarget Animal animal);
}