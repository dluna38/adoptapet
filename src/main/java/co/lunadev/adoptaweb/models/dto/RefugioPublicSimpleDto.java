package co.lunadev.adoptaweb.models.dto;

import co.lunadev.adoptaweb.models.Refugio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Refugio}
 */
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefugioPublicSimpleDto implements Serializable {
    @NotBlank
    private final String nombreRefugio;
    private final String descripcion;
    private final String slug;
    @JsonProperty("municipio")
    private final MunicipioPublicDto ubicacionMunicipio;
}