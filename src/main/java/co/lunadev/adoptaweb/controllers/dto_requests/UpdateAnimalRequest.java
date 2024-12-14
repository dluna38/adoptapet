package co.lunadev.adoptaweb.controllers.dto_requests;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class UpdateAnimalRequest {
    @NotBlank
    @Null
    private String nombre;
    @NotBlank
    @Null
    private String color;
    @Null
    private Animal.Tamano tamano;
    //solo contar el mes y año
    @Past
    @Null
    private LocalDate fechaNacimiento;
    @Null
    private boolean habilitadoAdopcion;
    @Null
    private boolean tieneChip;
    @Null
    private String chipCode;
    @Null
    private String descripcion;
    @Null
    private Raza raza;
    @Null
    private boolean estaEsterilizado;
    @Null
    private boolean estaVacunado;

    @Null
    private HistoriaClinica.EstadoGeneralAnimal estadoGeneral;
    @Null
    private HistoriaClinica.CondicionMedicaAnimal condicionMedica;
    @Null
    private HistoriaClinica.NecesidadEspecialAnimal necesidadesEspeciales;
    @Null
    private HistoriaClinica.ComportamientoAnimal comportamiento;
    private String observaciones;
    private String vacunas;
    private LocalDate ultimaRevisionVeterinaria;
}
