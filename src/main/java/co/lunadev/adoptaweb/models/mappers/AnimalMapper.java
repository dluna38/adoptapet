package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.models.Animal;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimalMapper {
    Animal toEntity(AnimalUpdateDto animalUpdateDto);

    AnimalUpdateDto toDto(Animal animal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "nombre",source = "nombre")
    void partialUpdate(AnimalUpdateDto animalUpdateDto, @MappingTarget Animal animal);
}