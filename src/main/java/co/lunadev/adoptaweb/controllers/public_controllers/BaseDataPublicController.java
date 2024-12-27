package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.FilterAnimal;
import co.lunadev.adoptaweb.services.models.DepartamentoService;
import co.lunadev.adoptaweb.services.models.EspecieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/base-data")
public class BaseDataPublicController {

    private final DepartamentoService departamentoService;
    private final EspecieService especieService;
    public BaseDataPublicController(DepartamentoService departamentoService, EspecieService especieService) {
        this.departamentoService = departamentoService;
        this.especieService = especieService;
    }

    @GetMapping("/filters/animal")
    public FilterAnimal getFiltersAnimal(){
        return new FilterAnimal(departamentoService.findAll(),especieService.getAllEspecies());
    }
}
