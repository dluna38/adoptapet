package co.lunadev.adoptaweb.controllers;


import co.lunadev.adoptaweb.controllers.dto_requests.LogInRequest;
import co.lunadev.adoptaweb.controllers.response.TokenResponse;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.services.mail.DispatcherEmail;
import co.lunadev.adoptaweb.services.models.UserService;
import co.lunadev.adoptaweb.utils.UtilString;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    private final DispatcherEmail dispatcherEmail;


    public AuthController(UserService userService, DispatcherEmail dispatcherEmail) {
        this.userService = userService;
        this.dispatcherEmail = dispatcherEmail;
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
    @GetMapping
    public ResponseEntity<Object> checkToken(){
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/test")
    public ResponseEntity<Object> test(){
        //dispatcherEmail.accountApprovedEmail().body("correo@corre.com","Refugio CEIBA","123").execute();
        return ResponseEntity.ok(UtilString.makeSlug("Refugio CEIBA rioñegró it's c    razy-stuff",35));
    }
}
