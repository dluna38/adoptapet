package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
import co.lunadev.adoptaweb.models.serializers.TamanoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String nombre;
    @NotBlank
    private String color;
    @Enumerated
    @JsonSerialize(using = TamanoSerializer.class)
    private Tamano tamano;
    //solo contar el mes y año
    @NotNull
    private LocalDate fechaNacimiento;
    @ColumnDefault("0")
    private Boolean habilitadoAdopcion;
    @ColumnDefault("0")
    private Boolean tieneChip;
    @Column(unique=true)
    private String chipCode;
    @Column(length = 1000)
    private String descripcion;
    @OneToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @ToString.Exclude
    private HistoriaClinica historiaClinica;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "raza_id",foreignKey = @ForeignKey(name = "FK_RAZA_ANIMAL"))
    @NotNull
    @ToString.Exclude
    private Raza raza;

    @OneToMany(mappedBy = "animal",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<FotoAnimal> fotos;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    @ToString.Exclude
    private FotoAnimal fotoPortada;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Refugio refugio;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Getter
    public enum Tamano {
        PEQUENO("Pequeño"),MEDIANO("Mediano"),GRANDE("Grande");

        final String normalName;
        Tamano(String normalName) {
            this.normalName = normalName;
        }

    }

}
