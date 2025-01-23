package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.AnuncioRefugioPrivateDto;
import co.lunadev.adoptaweb.models.AnuncioRefugioPrivateDtoMapper;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.models.dto.NewAnuncioRefugioDto;
import co.lunadev.adoptaweb.repositories.AnunciosRefugioRepository;
import co.lunadev.adoptaweb.services.models.AnunciosRefugioService;
import co.lunadev.adoptaweb.utils.UtilPage;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/anuncios")
public class AnunciosRefugioController {

    private final AnunciosRefugioService anunciosRefugioService;
    private final AnunciosRefugioRepository anunciosRefugioRepository;
    private final AnuncioRefugioPrivateDtoMapper anuncioRefugioPrivateDtoMapper;

    public AnunciosRefugioController(AnunciosRefugioService anunciosRefugioService, AnunciosRefugioRepository anunciosRefugioRepository,
                                     AnuncioRefugioPrivateDtoMapper anuncioRefugioPrivateDtoMapper) {
        this.anunciosRefugioService = anunciosRefugioService;
        this.anunciosRefugioRepository = anunciosRefugioRepository;
        this.anuncioRefugioPrivateDtoMapper = anuncioRefugioPrivateDtoMapper;
    }

    @GetMapping
    public PageResponse<AnuncioRefugioPrivateDto> getAllAnuncios(@RequestParam(required = false) Map<String, String> requestParams, @AuthenticationPrincipal User user) {
        PageRequest pageable = UtilPage.paramsToPageRequest(requestParams).withSort(Sort.Direction.DESC,"createdAt");
        return new PageResponse<>(anunciosRefugioRepository.findAllByRefugio_Id(user.getRefugio().getId(), pageable).map(anuncioRefugioPrivateDtoMapper::toDto));
    }
    @PostMapping
    public void newAnuncio(@Valid @RequestBody NewAnuncioRefugioDto newAnuncioDto, @AuthenticationPrincipal User user) {
        anunciosRefugioService.create(newAnuncioDto,user);
    }
    @PutMapping("/{anuncioId}")
    public void editAnuncio(@PathVariable Long anuncioId,@Valid @RequestBody NewAnuncioRefugioDto newAnuncioDto) {
        anunciosRefugioService.edit(anuncioId,newAnuncioDto);
    }
    @DeleteMapping("/{anuncioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnuncio(@PathVariable Long anuncioId) {
        anunciosRefugioService.delete(anuncioId);
    }
}
