package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.models.archivos.FotoAnimal;
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
    private Tamano tamano;
    //solo contar el mes y año
    @NotNull
    private LocalDate fechaNacimiento;
    @ColumnDefault("0")
    private boolean habilitadoAdopcion;
    @ColumnDefault("0")
    private boolean tieneChip;
    @Column(unique=true)
    private String chipCode;
    @Column(length = 1000)
    private String descripcion;
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false,name = "raza_id",foreignKey = @ForeignKey(name = "FK_RAZA_ANIMAL"))
    @NotNull
    private Raza raza;
    @OneToMany(mappedBy = "animal",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<FotoAnimal> fotos;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn()
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

    public enum Tamano {
        PEQUENO,MEDIANO,GRANDE
    }

}
