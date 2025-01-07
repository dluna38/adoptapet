package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.DepYMunWithIdDto;
import co.lunadev.adoptaweb.models.Municipio;
import co.lunadev.adoptaweb.repositories.projections.MunicipioPublicProjection;
import co.lunadev.adoptaweb.services.models.MunicipioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/municipios")
public class MunicipioPublicController {

    private final MunicipioService municipioService;

    public MunicipioPublicController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping("/dep/{depId}")
    public DepYMunWithIdDto getMunicipios(@PathVariable Byte depId) {
        return municipioService.findAllPublic(depId);
    }
}
