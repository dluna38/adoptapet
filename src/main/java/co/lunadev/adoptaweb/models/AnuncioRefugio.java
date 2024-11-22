package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AnuncioRefugio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Refugio refugio;
    private String titulo;
    private String contenido;

}
