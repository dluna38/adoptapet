package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.models.archivos.FotosAnimal;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private String color;
    @Enumerated
    private Tamano tamano;
    //solo contar el mes y año
    private LocalDate fechaNacimiento;
    @ColumnDefault("0")
    private Boolean habilitadoAdopcion;
    @ColumnDefault("0")
    private Boolean tieneChip;
    private String chipCode;
    private String descripcion;
    @OneToOne
    private HistoriaClinica historiaClinica;
    @ManyToOne
    @JoinColumn(nullable = false,name = "raza_id",foreignKey = @ForeignKey(name = "FK_RAZA_ANIMAL"))
    private Raza raza;
    @OneToMany(mappedBy = "animal")
    private List<FotosAnimal> fotos;
    @ManyToOne
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
