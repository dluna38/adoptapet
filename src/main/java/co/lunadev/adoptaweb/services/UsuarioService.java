package co.lunadev.adoptaweb.services;

import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.Usuario;
import co.lunadev.adoptaweb.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario saveUsuario(Usuario usuario){
        validateUsuario(usuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    private void validateUsuario(Usuario usuario) {
        if(usuarioRepository.findUsuarioByCorreoIgnoreCase(usuario.getCorreo()).isPresent()){
            throw new ValidationException("correo","ya existe");
        }
    }

}
