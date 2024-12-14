package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.models.serializers.EstadoGeneralAnimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "historiaClinica",optional = false)
    private Animal animal;
    @ColumnDefault("0")
    @Column(nullable = false)
    private boolean estaEsterilizado;
    @ColumnDefault("0")
    @Column(nullable = false)
    private boolean estaVacunado;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @JsonSerialize(using = EstadoGeneralAnimalSerializer.class)
    private EstadoGeneralAnimal estadoGeneral;
    @Enumerated
    @Column(nullable = false)
    private CondicionMedicaAnimal condicionMedica;
    @Enumerated
    @Column(nullable = false)
    private NecesidadEspecialAnimal necesidadesEspeciales;
    @Enumerated
    @Column(nullable = false)
    private ComportamientoAnimal comportamiento;
    private String observaciones;
    private String vacunas;
    private LocalDate ultimaRevisionVeterinaria;

    @Getter
    public enum EstadoGeneralAnimal implements EnumBase{
        EXCELENTE("Excelente","Animal activo, con buen apetito y sin signos visibles de enfermedad."),
        BUENO("Bueno","Animal ligeramente menos activo, pero en general saludable"),
        REGULAR("Regular","Animal con algunos síntomas leves (leve tos, diarrea ocasional, etc.)."),
        MALO("Malo","Animal con síntomas evidentes de enfermedad (vómitos, dificultad para respirar, etc.)."),
        CRITICO("Crítico","Animal en estado grave, requiriendo atención médica inmediata.");

        private final String descripcion;
        private final String normalName;
        EstadoGeneralAnimal(String normalName,String descripcion) {
            this.descripcion = descripcion;
            this.normalName = normalName;
        }

        @Override
        public String normalName() {
            return this.normalName;
        }

        @Override
        public String getDescripcion() {
            return descripcion;
        }
    }
    @Getter
    public enum CondicionMedicaAnimal implements EnumBase{
        SANO("Sano","Sin enfermedades diagnosticadas."),
        ENFERMEDAD_CRONICA("Enfermedad Crónica","Enfermedad de larga duración (diabetes, enfermedad renal, etc.)."),
        ENFERMEDAD_AGUDA("Enfermedad Crónica","Enfermedad de reciente aparición (infección respiratoria, gastroenteritis, etc.)."),
        LESION("Lesión","Heridas, fracturas, etc."),
        PARASITOSIS("Parasitosis","Presencia de parásitos internos o externos."),
        OTRA("Otra","Especificar cualquier otra condición médica relevante (enfermedades de la piel, problemas dentales, etc.).");
        private final String descripcion;
        private final String normalName;
        CondicionMedicaAnimal(String normalName,String descripcion) {
            this.descripcion = descripcion;
            this.normalName = normalName;
        }

        @Override
        public String normalName() {
            return this.normalName;
        }
    }
    @Getter
    public enum ComportamientoAnimal implements EnumBase{
        AMIGABLE("Amigable","Sociable con personas y otros animales."),
        TIMIDO("Tímido","Necesita un ambiente tranquilo y un acercamiento gradual."),
        AGRESIVO("Agresivo","Puede mostrar signos de agresión hacia personas u otros animales."),
        MIEDOSO("Miedoso","Se asusta fácilmente."),
        HIPERACTIVO("Hiperactivo","Muestra un nivel de energía muy alto."),
        OTRO("Otro","Especificar cualquier otro comportamiento relevante (ej., destructivo, ansioso, etc.).");
        private final String descripcion;
        private final String normalName;
        ComportamientoAnimal(String normalName,String descripcion) {
            this.descripcion = descripcion;
            this.normalName = normalName;
        }

        @Override
        public String normalName() {
            return this.normalName;
        }
    }
    @Getter
    public enum NecesidadEspecialAnimal implements EnumBase{
        NINGUNA("ninguna","Animal que no requiere cuidados especiales."),
        DIETA_ESPECIAL("Dieta especial","Requiere una dieta específica por razones médicas."),
        MEDICADO("Medicado","Requiere administración regular de medicamentos."),
        CONFINAMIENTO("Confinamiento","Debe ser mantenido aislado por razones médicas o de comportamiento."),
        MANEJO_ESPECIAL("Manejo especial","Requiere un manejo especial debido a su condición (ej., animales ciegos, sordos, etc.).");
        private final String descripcion;
        private final String normalName;
        NecesidadEspecialAnimal(String normalName,String descripcion) {
            this.descripcion = descripcion;
            this.normalName = normalName;
        }

        @Override
        public String normalName() {
            return this.normalName;
        }
    }

}
