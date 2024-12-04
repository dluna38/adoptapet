package co.lunadev.adoptaweb.models;

import co.lunadev.adoptaweb.controllers.dto_requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.models.archivos.FotoPeticionRegistro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "peticion_registro")
@Data
public class PeticionRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombreRefugio;
    @Column(nullable = false)
    private String nombreEncargado;
    @Email
    @Column(nullable = false,unique = true)
    private String correo;
    @Column(length = 15,nullable = false)
    private String telefono;
    @Column(nullable = false,length = 1000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Municipio ubicacionMunicipio;

    private int numeroAnimales;
    @ColumnDefault("0")
    private boolean aprobado;

    @OneToMany(mappedBy = "peticionRegistro",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FotoPeticionRegistro> fotos;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public void parsePeticionRegistroRequest(NewPeticionRegistroRequest request){
        this.nombreEncargado = request.getNombreEncargado();
        this.nombreRefugio = request.getNombreRefugio();
        this.correo = request.getCorreo();
        this.telefono = request.getTelefono();
        this.descripcion = request.getDescripcion();
        this.ubicacionMunicipio = request.getUbicacionMunicipio();
        this.numeroAnimales = request.getNumeroAnimales();
    }
}
