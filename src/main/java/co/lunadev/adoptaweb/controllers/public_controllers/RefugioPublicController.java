package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.dto.RefugioPublicSimpleDto;
import co.lunadev.adoptaweb.models.dto.RefugioPublicDto;
import co.lunadev.adoptaweb.services.models.RefugioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping
    public ResponseEntity<PageResponse<RefugioPublicSimpleDto>> findAll(@RequestParam(required = false) Map<String, String> requestParams) {
        return  ResponseEntity.ok(refugioService.findAll(requestParams));
    }
}
