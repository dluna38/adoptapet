package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "especie_id",nullable = false,foreignKey = @ForeignKey(name = "FK_especie_raza"))
    private Especie especie;
}
