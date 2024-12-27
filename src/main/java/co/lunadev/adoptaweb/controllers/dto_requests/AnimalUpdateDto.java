package co.lunadev.adoptaweb.controllers.dto_requests;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.validators.annotations.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link co.lunadev.adoptaweb.models.Animal}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalUpdateDto implements Serializable {

    @NullOrNotBlank
    private String nombre;
    @NullOrNotBlank
    private String color;

    private Animal.Sexo sexo;
    private Animal.Tamano tamano;

    private LocalDate fechaNacimiento;

    private Boolean habilitadoAdopcion;

    private Boolean tieneChip;
    @NullOrNotBlank
    private String chipCode;
    @NullOrNotBlank
    private String descripcion;
}