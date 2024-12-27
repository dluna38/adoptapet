package co.lunadev.adoptaweb.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link co.lunadev.adoptaweb.models.Municipio}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioPublicDto implements Serializable {
    private String nombre;
    private DepartamentoPublicDto departamento;
}