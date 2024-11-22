package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FotosAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private static final  String DIRECTORY_PATH="images/fotos_animal/";
    private String nombreInterno;
    private String nombreOriginalFoto;

    @ManyToOne
    private Animal animal;
}
