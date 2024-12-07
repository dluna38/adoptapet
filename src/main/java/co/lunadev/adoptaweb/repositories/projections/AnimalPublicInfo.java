package co.lunadev.adoptaweb.repositories.projections;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Projection for {@link co.lunadev.adoptaweb.models.Animal}
 */
public interface AnimalPublicInfo {
    String getNombre();

    String getColor();

    Animal.Tamano getTamano();

    LocalDate getFechaNacimiento();

    String getDescripcion();

    LocalDateTime getUpdatedAt();

    HistoriaClinicaInfo getHistoriaClinica();

    //List<FotosAnimalInfo> getFotos();

    RefugioInfo getRefugio();

    Raza getRaza();

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
     * Projection for {@link co.lunadev.adoptaweb.models.archivos.FotosAnimal}
     */
    interface FotosAnimalInfo {
        String getNombreInterno();
    }

    /**
     * Projection for {@link co.lunadev.adoptaweb.models.Refugio}
     */
    interface RefugioInfo {
        String getNombreRefugio();
    }
}