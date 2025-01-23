package co.lunadev.adoptaweb.models.archivos;

import co.lunadev.adoptaweb.models.Animal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FotoAnimal extends BaseArchivo {

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Animal animal;

    @Transient
    @ToString.Exclude
    public static final short MAX_FILES = 3;
    @Transient
    public static final String DIRECTORY_PATH="src/main/resources/images/fotos_animal/";

    public FotoAnimal(String path, String nombreInterno, String nombreOriginalFoto, Animal animal) {
        super(path, nombreInterno, nombreOriginalFoto);
        this.animal = animal;
    }
}
