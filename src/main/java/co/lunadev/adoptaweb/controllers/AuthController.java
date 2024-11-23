package co.lunadev.adoptaweb.controllers;


import co.lunadev.adoptaweb.controllers.custom.requests.LogInRequest;
import co.lunadev.adoptaweb.controllers.custom.response.TokenResponse;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.services.mail.EmailSender;
import co.lunadev.adoptaweb.services.mail.RegistrationReceivedEmail;
import co.lunadev.adoptaweb.services.models.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    private final RegistrationReceivedEmail registrationReceivedEmail;

    public AuthController(UserService userService, RegistrationReceivedEmail registrationReceivedEmail) {
        this.userService = userService;
        this.registrationReceivedEmail = registrationReceivedEmail;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> logInUsuario(@RequestBody @Valid LogInRequest logInRequest){
        return ResponseEntity.ok(userService.logInUsuario(logInRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> registerUsuario(@RequestBody @Valid User user){
        userService.registerUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test(){
        return ResponseEntity.ok(registrationReceivedEmail.sendMail("Refugio CEIBA","correo@corre.com"));
    }
}
