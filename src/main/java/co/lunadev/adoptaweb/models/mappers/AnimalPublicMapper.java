package co.lunadev.adoptaweb.models.mappers;

import co.lunadev.adoptaweb.controllers.dto_requests.AnimalUpdateDto;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.AnimalPublicDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimalPublicMapper {
    AnimalPublicDto toDto(Animal animal);

}