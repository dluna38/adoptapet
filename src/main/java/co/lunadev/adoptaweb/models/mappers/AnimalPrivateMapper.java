package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.dto.AnimalDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimalPrivateMapper {
    Animal toEntity(AnimalDto animalDto);

    AnimalDto toDto(Animal animal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Animal partialUpdate(AnimalDto animalDto, @MappingTarget Animal animal);
}