package co.lunadev.adoptaweb.models.dto;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.Especie;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.models.serializers.EnumOrdinalSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Animal}
 */
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalRawDto implements Serializable {
    private final Long id;
    @NotBlank
    private final String nombre;
    @NotBlank
    private final String color;
    private final Animal.Sexo sexo;
    private final Animal.Tamano tamano;
    @NotNull
    private final LocalDate fechaNacimiento;
    private final Boolean habilitadoAdopcion;
    private final Boolean tieneChip;
    private final String chipCode;
    private final String descripcion;
    private final HistoriaClinicaRawDto historiaClinica;
    @NotNull
    private final AnimalRawDto.RazaRawDto raza;
    private final AnimalPublicDto.FotoAnimalDto fotoPortada;

    /**
     * DTO for {@link HistoriaClinica}
     */
    @AllArgsConstructor
    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HistoriaClinicaRawDto implements Serializable {
        private final Long id;
        private final boolean estaEsterilizado;
        private final boolean estaVacunado;
        @JsonSerialize(using = EnumOrdinalSerializer.class)
        private final HistoriaClinica.EstadoGeneralAnimal estadoGeneral;
        @JsonSerialize(using = EnumOrdinalSerializer.class)
        private final HistoriaClinica.CondicionMedicaAnimal condicionMedica;
        @JsonSerialize(using = EnumOrdinalSerializer.class)
        private final HistoriaClinica.NecesidadEspecialAnimal necesidadesEspeciales;
        @JsonSerialize(using = EnumOrdinalSerializer.class)
        private final HistoriaClinica.ComportamientoAnimal comportamiento;
        private final String observaciones;
        private final String vacunas;
        private final LocalDate ultimaRevisionVeterinaria;
    }

    /**
     * DTO for {@link Raza}
     */
    @AllArgsConstructor
    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RazaRawDto implements Serializable {
        private final Long id;
        private final String nombre;
        private final EspecieRawDto especie;

        /**
         * DTO for {@link Especie}
         */
        @AllArgsConstructor
        @Getter
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EspecieRawDto implements Serializable {
            private final Short id;
            private final String nombre;
        }
    }
}