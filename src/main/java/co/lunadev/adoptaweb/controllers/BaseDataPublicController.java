package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.response.FilterAnimal;
import co.lunadev.adoptaweb.models.Especie;
import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.services.models.DepartamentoService;
import co.lunadev.adoptaweb.services.models.EspecieService;
import co.lunadev.adoptaweb.services.models.RazaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base-data")
public class BaseDataPublicController {

    private final EspecieService especieService;
    private final RazaService razaService;

    public BaseDataPublicController(EspecieService especieService, RazaService razaService) {
        this.especieService = especieService;
        this.razaService = razaService;
    }

    @GetMapping("/especies")
    @ResponseStatus(HttpStatus.OK)
    public List<Especie> getEspecies(){
        return especieService.getAllEspecies();
    }
    @GetMapping("/razas/especie/{idEspecie}")
    @ResponseStatus(HttpStatus.OK)
    public List<Raza> getRazasEspecies(@PathVariable Short idEspecie){
        return razaService.getRazasByEspecieId(idEspecie);
    }
}
