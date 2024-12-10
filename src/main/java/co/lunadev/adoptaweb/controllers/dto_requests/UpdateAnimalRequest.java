package co.lunadev.adoptaweb.controllers.dto_requests;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class UpdateAnimalRequest {
    @NotBlank
    private String nombre;
    @NotBlank
    private String color;
    @NotNull
    private Animal.Tamano tamano;
    //solo contar el mes y año
    @Past
    @NotNull
    private LocalDate fechaNacimiento;
    @NotNull
    private boolean habilitadoAdopcion;
    @NotNull
    private boolean tieneChip;
    private String chipCode;
    @NotNull
    private String descripcion;
    @NotNull
    private Raza raza;
    private List<MultipartFile> fotos;

    @NotNull
    private boolean estaEsterilizado;
    @NotNull
    private boolean estaVacunado;

    @NotNull
    private HistoriaClinica.EstadoGeneralAnimal estadoGeneral;
    @NotNull
    private HistoriaClinica.CondicionMedicaAnimal condicionMedica;
    @NotNull
    private HistoriaClinica.NecesidadEspecialAnimal necesidadesEspeciales;
    @NotNull
    private HistoriaClinica.ComportamientoAnimal comportamiento;
    private String observaciones;
    private String vacunas;
    private LocalDate ultimaRevisionVeterinaria;
}
