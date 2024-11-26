package co.lunadev.adoptaweb.models.archivos;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.archivos.BaseArchivos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class FotosAnimal extends BaseArchivos {

    @ManyToOne
    private Animal animal;
    @Transient
    private static final String DIRECTORY_PATH="src/main/resources/images/fotos_animal/";
}
