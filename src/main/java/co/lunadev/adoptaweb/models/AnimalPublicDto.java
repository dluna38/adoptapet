package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.models.serializers.EnumBaseSerializer;
import co.lunadev.adoptaweb.models.serializers.TamanoSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Animal}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalPublicDto implements Serializable {
    @NotBlank
    private String nombre;
    @NotBlank
    private String color;
    @JsonSerialize(using = TamanoSerializer.class)
    private Animal.Tamano tamano;
    @NotNull
    private LocalDate fechaNacimiento;
    private Boolean tieneChip;
    private String descripcion;
    private HistoriaClinicaDto historiaClinica;
    @NotNull
    private AnimalPublicDto.RazaDto raza;

    private FotoAnimalDto fotoPortada;
    private RefugioDto refugio;
    private LocalDateTime updatedAt;

    /**
     * DTO for {@link HistoriaClinica}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HistoriaClinicaDto implements Serializable {
        private boolean estaEsterilizado;
        private boolean estaVacunado;
        @JsonSerialize(using = EnumBaseSerializer.class)
        private HistoriaClinica.EstadoGeneralAnimal estadoGeneral;
        @JsonSerialize(using = EnumBaseSerializer.class)
        private HistoriaClinica.CondicionMedicaAnimal condicionMedica;
        @JsonSerialize(using = EnumBaseSerializer.class)
        private HistoriaClinica.NecesidadEspecialAnimal necesidadesEspeciales;
        @JsonSerialize(using = EnumBaseSerializer.class)
        private HistoriaClinica.ComportamientoAnimal comportamiento;
        private String observaciones;
        private String vacunas;
        private LocalDate ultimaRevisionVeterinaria;
    }

    /**
     * DTO for {@link Raza}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RazaDto implements Serializable {
        private String nombre;
        private EspecieDto especie;

        /**
         * DTO for {@link Especie}
         */
        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        @ToString
        @Accessors(chain = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EspecieDto implements Serializable {
            private String nombre;
        }
    }

    /**
     * DTO for {@link co.lunadev.adoptaweb.models.archivos.FotoAnimal}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FotoAnimalDto implements Serializable {
        @JsonProperty("img")
        private String nombreInterno;
    }

    /**
     * DTO for {@link Refugio}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RefugioDto implements Serializable {
        @NotBlank
        private String nombreRefugio;
        private String slug;
    }
}