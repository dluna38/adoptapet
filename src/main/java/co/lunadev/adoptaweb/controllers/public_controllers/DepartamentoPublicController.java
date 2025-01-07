package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.DepYMunWithIdDto;
import co.lunadev.adoptaweb.models.Departamento;
import co.lunadev.adoptaweb.models.dto.DepartamentoPublicDto;
import co.lunadev.adoptaweb.services.models.DepartamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/departamentos")
public class DepartamentoPublicController {
    private final DepartamentoService departamentoService;

    public DepartamentoPublicController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Departamento> getMunicipios() {
        return departamentoService.findAllDepartamentos();
    }
}
