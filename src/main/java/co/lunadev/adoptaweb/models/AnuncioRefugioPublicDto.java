package co.lunadev.adoptaweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link AnuncioRefugio}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnuncioRefugioPublicDto implements Serializable {
    private String titulo;
    private String contenido;
    private LocalDateTime createdAt;
}