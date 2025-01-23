package co.lunadev.adoptaweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link AnuncioRefugio}
 */
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnuncioRefugioPrivateDto implements Serializable {
    private final Long id;
    private final String titulo;
    private final String contenido;
    private final Boolean publicado;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}