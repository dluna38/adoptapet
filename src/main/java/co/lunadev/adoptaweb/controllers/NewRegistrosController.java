package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.custom.requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.exceptions.FieldRequiredException;
import co.lunadev.adoptaweb.models.PeticionRegistro;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.services.models.PeticionRegistroService;
import co.lunadev.adoptaweb.validators.PeticionRegistroValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class NewRegistrosController {


    private final PeticionRegistroService peticionRegistroService;

    public NewRegistrosController(PeticionRegistroService peticionRegistroService) {
        this.peticionRegistroService = peticionRegistroService;

    }

    @PostMapping()
    public ResponseEntity<Void> newRegistro(@Valid @RequestPart(name = "datos") NewPeticionRegistroRequest request,
                                              @RequestPart(name = "fotos",required = false) List<MultipartFile> fotos) {
        request.setFotos(fotos);
        peticionRegistroService.saveNewPeticionRegistro(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PeticionRegistro>> getPeticionRegistro(){
        return ResponseEntity.ok(peticionRegistroService.getAllPeticionesRegistro());
    }
}
