package co.lunadev.adoptaweb.models.archivos;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.archivos.BaseArchivos;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FotosAnimal extends BaseArchivos {

    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Animal animal;
    @Transient
    public static final short MAX_FILES = 3;
    @Transient
    public static final String DIRECTORY_PATH="src/main/resources/images/fotos_animal/";

    public FotosAnimal(String path, String nombreInterno, String nombreOriginalFoto, Animal animal) {
        super(path, nombreInterno, nombreOriginalFoto);
        this.animal = animal;
    }
}
