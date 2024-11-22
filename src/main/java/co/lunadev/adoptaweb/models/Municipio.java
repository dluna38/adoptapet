package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String nombre;
    @ManyToOne
    private Departamento departamento;
}
