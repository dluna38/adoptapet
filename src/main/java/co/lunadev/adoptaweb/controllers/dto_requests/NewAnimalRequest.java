package co.lunadev.adoptaweb.controllers.dto_requests;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import co.lunadev.adoptaweb.models.Raza;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class NewAnimalRequest {

    @NotBlank
    private String nombre;
    @NotBlank
    private String color;
    @NotNull
    private Animal.Tamano tamano;
    @NotNull
    private Animal.Sexo sexo;
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


    public Animal getAnimal() {
        Animal animal = new Animal();
        animal.setNombre(nombre);
        animal.setColor(color);
        animal.setTamano(tamano);
        animal.setFechaNacimiento(fechaNacimiento);
        animal.setHabilitadoAdopcion(habilitadoAdopcion);
        animal.setTieneChip(tieneChip);
        animal.setChipCode(chipCode);
        animal.setDescripcion(descripcion);
        animal.setRaza(raza);
        animal.setSexo(sexo);
        return animal;
    }

    public HistoriaClinica getHistoriaClinica() {
        HistoriaClinica hc = new HistoriaClinica();
        hc.setObservaciones(observaciones);
        hc.setVacunas(vacunas);
        hc.setEstadoGeneral(estadoGeneral);
        hc.setCondicionMedica(condicionMedica);
        hc.setComportamiento(comportamiento);
        hc.setNecesidadesEspeciales(necesidadesEspeciales);
        hc.setUltimaRevisionVeterinaria(ultimaRevisionVeterinaria);
        hc.setEstaEsterilizado(estaEsterilizado);
        hc.setEstaVacunado(estaVacunado);
        return hc;
    }

}
