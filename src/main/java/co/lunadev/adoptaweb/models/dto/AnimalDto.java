package co.lunadev.adoptaweb.models.dto;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.serializers.EnumBaseSerializer;
import co.lunadev.adoptaweb.models.serializers.TamanoSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link co.lunadev.adoptaweb.models.Animal}
 */
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalDto implements Serializable {
    private final Long id;
    @NotBlank
    private final String nombre;
    @NotBlank
    private final String color;
    @JsonSerialize(using = EnumBaseSerializer.class)
    private final Animal.Sexo sexo;
    @JsonSerialize(using = TamanoSerializer.class)
    private final Animal.Tamano tamano;
    @NotNull
    private final LocalDate fechaNacimiento;
    private final Boolean habilitadoAdopcion;
    private final Boolean tieneChip;
    private final String chipCode;
    private final String descripcion;

    private final AnimalPublicDto.HistoriaClinicaDto historiaClinica;
    @NotNull
    private final AnimalPublicDto.RazaDto raza;
    private final AnimalPublicDto.FotoAnimalDto fotoPortada;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}