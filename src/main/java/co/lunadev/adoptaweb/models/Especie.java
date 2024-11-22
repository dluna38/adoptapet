package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "especie")
    private List<Raza> razas;

    @PrePersist
    public void prePersistNombre(){
        this.nombre = this.nombre.toUpperCase();
    }
}
