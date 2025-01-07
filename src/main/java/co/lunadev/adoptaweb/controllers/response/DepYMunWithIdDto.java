package co.lunadev.adoptaweb.controllers.response;

import co.lunadev.adoptaweb.models.MunicipioPublicWithIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class DepYMunWithIdDto {
    private Byte idDepartamento;
    private List<MunicipioPublicWithIdDto> municipios;
}
