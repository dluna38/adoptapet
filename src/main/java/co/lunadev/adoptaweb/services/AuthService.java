package co.lunadev.adoptaweb.services;


import co.lunadev.adoptaweb.controllers.custom.requests.LogInRequest;
import co.lunadev.adoptaweb.controllers.custom.response.TokenResponse;
import co.lunadev.adoptaweb.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponse logInUsuario(LogInRequest credentials){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getCorreo(),credentials.getContrasena()
        ));
        return new TokenResponse(jwtService.generateToken((UserDetails) auth.getPrincipal()));
    }

}
