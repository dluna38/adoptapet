package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Refugio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "refugio")
    @ToString.Exclude
    private User usuario;
    @NotBlank
    @Column(nullable = false)
    private String nombreRefugio;
    @Column(nullable = false)
    @NotBlank
    private String nombreEncargado;
    @Email
    @Column(nullable = false,unique = true)
    private String correo;
    @Column(length = 1000)
    private String descripcion;
    @Column(length = 15,nullable = false)
    private String telefono;

    @ColumnDefault("0")
    private Boolean verificado;
    @ColumnDefault("1")
    private Boolean habilitado;
    @Column(unique = true,length = 35)
    private String slug;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Municipio ubicacionMunicipio;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    @ToString.Exclude
    private List<AnuncioRefugio> anuncios;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    @ToString.Exclude
    private List<Animal> animales;

    public Refugio(Long id) {
        this.id = id;
    }
}
