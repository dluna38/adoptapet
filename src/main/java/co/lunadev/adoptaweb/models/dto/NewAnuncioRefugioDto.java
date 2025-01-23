package co.lunadev.adoptaweb.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link co.lunadev.adoptaweb.models.AnuncioRefugio}
 */
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewAnuncioRefugioDto implements Serializable {
    @NotNull
    @NotBlank
    private final String titulo;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 10000)
    private final String contenido;
    @NotNull
    private final Boolean publicado;
}