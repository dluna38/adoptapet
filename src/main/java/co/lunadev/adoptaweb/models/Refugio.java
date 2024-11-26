package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Data
public class Refugio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @ToString.Exclude
    private User usuario;

    private String nombreRefugio;
    private String nombreEncargado;
    @Email
    @Column(nullable = false,unique = true)
    private String correo;
    private String descripcion;
    @Column(length = 15,nullable = false)
    private String telefono;

    @ColumnDefault("0")
    private Boolean verificado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio ubicacionMunicipio;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    private List<AnuncioRefugio> anuncios;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    private List<Animal> animales;
}
