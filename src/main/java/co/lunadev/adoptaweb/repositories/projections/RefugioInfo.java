package co.lunadev.adoptaweb.repositories.projections;

import co.lunadev.adoptaweb.models.*;
import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.models.serializers.TamanoSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Projection for {@link Refugio}
 */
public interface RefugioInfo {
    String getNombreRefugio();

    String getDescripcion();

    String getTelefono();

    Boolean isVerificado();

    String getSlug();

    //MunicipioInfo getUbicacionMunicipio();

    //List<AnuncioRefugioInfo> getAnuncios();

    //List<AnimalInfo> getAnimales();

    /**
     * Projection for {@link Municipio}
     */
    interface MunicipioInfo {
        String getNombre();

        DepartamentoInfo getDepartamento();

        /**
         * Projection for {@link Departamento}
         */
        interface DepartamentoInfo {
            String getNombre();
        }
    }

    /**
     * Projection for {@link AnuncioRefugio}
     */
    interface AnuncioRefugioInfo {
        String getTitulo();

        String getContenido();
    }

    /**
     * Projection for {@link Animal}
     */
    interface AnimalInfo {
        String getNombre();

        String getColor();
        @JsonSerialize(using = TamanoSerializer.class)
        Animal.Tamano getTamano();

        LocalDate getFechaNacimiento();

        String getDescripcion();

        LocalDateTime getUpdatedAt();

        HistoriaClinicaInfo getHistoriaClinica();

        FotosAnimalInfo getFotoPortada();

        RazaInfo getRaza();
    }
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

    interface RazaInfo {
        String getNombre();
        AnimalPublicInfo.EspecieInfo getEspecie();
    }
    interface EspecieInfo {
        String getNombre();
    }
}