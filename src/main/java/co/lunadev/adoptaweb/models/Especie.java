package co.lunadev.adoptaweb.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "especie")
    @ToString.Exclude
    private List<Raza> razas;

    @PrePersist
    public void prePersistNombre(){
        this.nombre = this.nombre.toUpperCase();
    }

}
