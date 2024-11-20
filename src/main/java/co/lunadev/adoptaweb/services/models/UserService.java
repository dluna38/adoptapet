package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUsuario(User user){
        validateUsuario(user);
        user.setRole(User.Rol.ROLE_USUARIO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void validateUsuario(User user) {
        if(userRepository.findUsuarioByEmailIgnoreCase(user.getEmail()).isPresent()){
            throw new ValidationException("correo","ya existe");
        }
    }

}
