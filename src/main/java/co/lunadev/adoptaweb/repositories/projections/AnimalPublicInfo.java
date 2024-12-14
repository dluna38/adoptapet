package co.lunadev.adoptaweb.repositories.projections;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.models.serializers.TamanoSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Projection for {@link co.lunadev.adoptaweb.models.Animal}
 */
public interface AnimalPublicInfo {
    String getNombre();

    String getColor();
    @JsonSerialize(using = TamanoSerializer.class)
    Animal.Tamano getTamano();

    LocalDate getFechaNacimiento();

    String getDescripcion();

    LocalDateTime getUpdatedAt();

    HistoriaClinicaInfo getHistoriaClinica();

   FotosAnimalInfo getFotoPortada();

    RefugioInfo getRefugio();

    RazaInfo getRaza();

    /**
     * Projection for {@link co.lunadev.adoptaweb.models.HistoriaClinica}
     */
    interface HistoriaClinicaInfo {
        boolean isEstaEsterilizado();

        boolean isEstaVacunado();

        HistoriaClinica.EstadoGeneralAnimal getEstadoGeneral();

        HistoriaClinica.CondicionMedicaAnimal getCondicionMedica();

        HistoriaClinica.NecesidadEspecialAnimal getNecesidadesEspeciales();

        HistoriaClinica.ComportamientoAnimal getComportamiento();

        String getVacunas();

        LocalDate getUltimaRevisionVeterinaria();
    }

    /**
     * Projection for {@link FotoAnimal}
     */
    interface FotosAnimalInfo {
        @JsonProperty("img")
        String getNombreInterno();
    }

    /**
     * Projection for {@link co.lunadev.adoptaweb.models.Refugio}
     */
    interface RefugioInfo {
        String getNombreRefugio();
    }

    interface RazaInfo {
        String getNombre();
        EspecieInfo getEspecie();
    }
    interface EspecieInfo {
        String getNombre();
    }
}