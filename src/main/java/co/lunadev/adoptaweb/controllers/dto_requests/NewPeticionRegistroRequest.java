package co.lunadev.adoptaweb.controllers.dto_requests;

import co.lunadev.adoptaweb.models.Municipio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class NewPeticionRegistroRequest {
    @NotBlank
    private String nombreRefugio;
    @NotBlank
    private String nombreEncargado;
    @Email
    @NotBlank
    private String correo;
    @NotBlank
    private String telefono;
    @NotBlank
    private String descripcion;
    @NotNull
    private Municipio ubicacionMunicipio;
    @NotNull
    @Positive
    private int numeroAnimales;

    private List<MultipartFile> fotos;
}
