package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Data
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "historiaClinica",optional = false)
    private Animal animal;
    @ColumnDefault("0")
    private Boolean estaEsterilizado;
    @ColumnDefault("0")
    private Boolean estaVacunado;
    @Enumerated(EnumType.ORDINAL)
    private EstadoGeneralAnimal estadoGeneral;
    @Enumerated
    private CondicionMedicaAnimal condicionMedica;
    @Enumerated
    private NecesidadEspecialAnimal necesidadesEspeciales;
    @Enumerated
    private ComportamientoAnimal comportamiento;
    private String observaciones;
    private String vacunas;
    private LocalDate ultimaRevisionVeterinaria;

    @Getter
    public enum EstadoGeneralAnimal{
        EXCELENTE("Animal activo, con buen apetito y sin signos visibles de enfermedad."),
        BUENO("Animal ligeramente menos activo, pero en general saludable"),
        REGULAR("Animal con algunos síntomas leves (leve tos, diarrea ocasional, etc.)."),
        MALO("Animal con síntomas evidentes de enfermedad (vómitos, dificultad para respirar, etc.)."),
        CRITICO("Animal en estado grave, requiriendo atención médica inmediata.");

        private final String descripcion;
        EstadoGeneralAnimal(String descripcion) {
            this.descripcion = descripcion;
        }

    }
    @Getter
    public enum CondicionMedicaAnimal{
        SANO("Sin enfermedades diagnosticadas."),
        ENFERMEDAD_CRONICA("Enfermedad de larga duración (diabetes, enfermedad renal, etc.)."),
        ENFERMEDAD_AGUDA("Enfermedad de reciente aparición (infección respiratoria, gastroenteritis, etc.)."),
        LESION("Heridas, fracturas, etc."),
        PARASITOSIS("Presencia de parásitos internos o externos."),
        OTRA("Especificar cualquier otra condición médica relevante (enfermedades de la piel, problemas dentales, etc.).");
        private final String descripcion;
        CondicionMedicaAnimal(String descripcion) {
            this.descripcion = descripcion;
        }

    }
    @Getter
    public enum ComportamientoAnimal{
        AMIGABLE("Sociable con personas y otros animales."),
        TIMIDO("Necesita un ambiente tranquilo y un acercamiento gradual."),
        AGRESIVO("Puede mostrar signos de agresión hacia personas u otros animales."),
        MIEDOSO("Se asusta fácilmente."),
        HIPERACTIVO("Muestra un nivel de energía muy alto."),
        OTRO("Especificar cualquier otro comportamiento relevante (ej., destructivo, ansioso, etc.).");
        private final String descripcion;
        ComportamientoAnimal(String descripcion) {
            this.descripcion = descripcion;
        }
    }
    @Getter
    public enum NecesidadEspecialAnimal{
        NINGUNA("Animal que no requiere cuidados especiales."),
        DIETA_ESPECIAL("Requiere una dieta específica por razones médicas."),
        MEDICADO("Requiere administración regular de medicamentos."),
        CONFINAMIENTO("Debe ser mantenido aislado por razones médicas o de comportamiento."),
        MANEJO_ESPECIAL("Requiere un manejo especial debido a su condición (ej., animales ciegos, sordos, etc.).");
        private final String descripcion;
        NecesidadEspecialAnimal(String descripcion) {
            this.descripcion = descripcion;
        }
    }
}
