package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.dto_requests.EnumDto;
import co.lunadev.adoptaweb.controllers.response.EstadosClinica;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/histo-clinic")
public class HistoriaClinicaController {

    public void editHistoriaClinica() {

    }
    @GetMapping("/estados")
    public ResponseEntity<Map<String,List<EnumDto>>> getEstadosHistoriaClinica() {
        return ResponseEntity.ok(EstadosClinica.getEstadosClinica());
    }
}
