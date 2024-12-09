package co.lunadev.adoptaweb.models.archivos;

import co.lunadev.adoptaweb.models.PeticionRegistro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FotoPeticionRegistro extends BaseArchivo {

    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PeticionRegistro peticionRegistro;
    @Transient
    private static final short MAX_FILES = 3;
    @Transient
    public static final String DIRECTORY_PATH="src/main/resources/images/fotos_registro/";

    public FotoPeticionRegistro(String nombreInterno, String nombreOriginalFoto, PeticionRegistro peticionRegistro) {
        super(nombreInterno, nombreOriginalFoto);
        this.peticionRegistro = peticionRegistro;
    }

    public FotoPeticionRegistro(String path, String nombreInterno, String nombreOriginalFoto, PeticionRegistro peticionRegistro) {
        super(path, nombreInterno, nombreOriginalFoto);
        this.peticionRegistro = peticionRegistro;
    }

    public FotoPeticionRegistro(String nombreInterno, String nombreOriginalFoto) {
        super(nombreInterno, nombreOriginalFoto);
    }
}
