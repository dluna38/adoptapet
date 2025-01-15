package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id",nullable = false,foreignKey = @ForeignKey(name = "FK_especie_raza"))
    @ToString.Exclude
    private Especie especie;

}
