package co.lunadev.adoptaweb.models.dto;

import co.lunadev.adoptaweb.models.AnuncioRefugioPublicDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link co.lunadev.adoptaweb.models.Refugio}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefugioPublicDto implements Serializable {
    @NotBlank
    @JsonProperty("nombre")
    private String nombreRefugio;
    @Email
    private String correo;
    private String descripcion;
    private String telefono;
    private Boolean verificado;
    private MunicipioPublicDto ubicacionMunicipio;
    private List<AnimalPublicDto> animales;
    private List<AnuncioRefugioPublicDto> anuncios;
}