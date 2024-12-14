package co.lunadev.adoptaweb.repositories.projections;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.Refugio;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Projection for {@link Animal}
 */
public interface AnimalWithRefugio {
    Long getId();

    String getNombre();

    String getColor();

    Animal.Tamano getTamano();

    LocalDate getFechaNacimiento();

    boolean isHabilitadoAdopcion();

    boolean isTieneChip();

    String getChipCode();

    String getDescripcion();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Refugio getRefugio();
}