package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.RefugioPublicDto;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.repositories.projections.RefugioInfo;
import co.lunadev.adoptaweb.services.models.RefugioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/refugio")
public class RefugioPublicController {

    private final RefugioService refugioService;

    public RefugioPublicController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }
    @GetMapping("/slug/{slug}")
    public ResponseEntity<RefugioPublicDto> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(refugioService.findRefugioBySlug(slug));
    }
}
