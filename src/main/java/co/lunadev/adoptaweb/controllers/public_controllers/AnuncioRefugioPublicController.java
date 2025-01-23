package co.lunadev.adoptaweb.controllers.public_controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.AnuncioRefugioPublicDto;
import co.lunadev.adoptaweb.services.models.AnunciosRefugioService;
import co.lunadev.adoptaweb.utils.UtilPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public/anuncios")
public class AnuncioRefugioPublicController {

    private final AnunciosRefugioService anunciosRefugioService;

    public AnuncioRefugioPublicController(AnunciosRefugioService anunciosRefugioService) {
        this.anunciosRefugioService = anunciosRefugioService;
    }

    @GetMapping("/r/{slug}")
    public PageResponse<AnuncioRefugioPublicDto> getAnunciosBySlug(@RequestParam(required = false) Map<String, String> requestParams,@PathVariable("slug") String slug) {
        PageRequest pageable = UtilPage.paramsToPageRequestOnlyPageNumber(6,requestParams).withSort(Sort.Direction.DESC,"createdAt");
        return new PageResponse<>(anunciosRefugioService.findBySlug(slug,pageable));
    }
}
