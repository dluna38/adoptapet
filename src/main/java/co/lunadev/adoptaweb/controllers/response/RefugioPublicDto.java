package co.lunadev.adoptaweb.controllers.response;

import co.lunadev.adoptaweb.models.AnimalPublicDto;
import co.lunadev.adoptaweb.models.AnuncioRefugio;
import co.lunadev.adoptaweb.models.Refugio;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefugioPublicDto {
    private Refugio refugio;
    private List<AnimalPublicDto> animals;
    private List<AnuncioRefugio> anuncios;
}
