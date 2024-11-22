package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Data
public class Refugio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User usuario;
    private String nombre;
    @Email
    private String correo;
    private String descripcion;
    @ColumnDefault("0")
    private Boolean verificado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    private List<AnuncioRefugio> anuncios;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "refugio")
    private List<Animal> animales;
}
