package co.lunadev.adoptaweb.services.models;

import co.lunadev.adoptaweb.controllers.custom.requests.LogInRequest;
import co.lunadev.adoptaweb.controllers.custom.response.TokenResponse;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.repositories.UserRepository;
import co.lunadev.adoptaweb.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponse logInUsuario(LogInRequest credentials){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getCorreo(),credentials.getContrasena()
        ));
        return new TokenResponse(jwtService.generateToken((UserDetails) auth.getPrincipal()));
    }

    public void registerUsuario(User user){
        saveUsuario(user);
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
