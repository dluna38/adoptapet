package co.lunadev.adoptaweb.models.archivos;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
public class BaseArchivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO agregar ubicacion = ruta, nombreIterno solo nomber, agregar tipo??
    private String path;
    private String nombreInterno;
    private String nombreOriginalFoto;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected BaseArchivo(String nombreInterno, String nombreOriginalFoto) {
        this.nombreInterno = nombreInterno;
        this.nombreOriginalFoto = nombreOriginalFoto;
    }

    public BaseArchivo(String path, String nombreInterno, String nombreOriginalFoto) {
        this.path = path;
        this.nombreInterno = nombreInterno;
        this.nombreOriginalFoto = nombreOriginalFoto;
    }
}
