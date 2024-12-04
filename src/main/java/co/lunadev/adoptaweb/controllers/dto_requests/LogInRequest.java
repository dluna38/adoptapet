package co.lunadev.adoptaweb.controllers.dto_requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LogInRequest {
    @NotBlank(message = "El correo no puede estar vació")
    @Email(message = "No es un correo valido")
    String correo;
    @NotBlank(message = "La contraseña no puede estar vaciá")
    String contrasena;

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
