package co.lunadev.adoptaweb.controllers.response;

import co.lunadev.adoptaweb.controllers.dto_requests.EnumDto;
import co.lunadev.adoptaweb.models.EnumBase;
import co.lunadev.adoptaweb.models.HistoriaClinica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class EstadosClinica {

    public static final List<Class<? extends Enum>> enumClasses = List.of(
            HistoriaClinica.EstadoGeneralAnimal.class,
            HistoriaClinica.CondicionMedicaAnimal.class,
            HistoriaClinica.ComportamientoAnimal.class,
            HistoriaClinica.NecesidadEspecialAnimal.class
    );

    public static <E extends Enum<E>> Map<String, Integer> getInfo(Class<E> enumClass) {
        return EnumSet.allOf(enumClass).stream()
                .collect(Collectors.toMap(Enum::name, Enum::ordinal));
    }
    public static Map<String, Map<String, Integer>> getInfo2() {
         return enumClasses.stream()
                .collect(Collectors.toMap(Class::getSimpleName, EstadosClinica::getInfo));
    }

    public static Map<String, List<EnumDto>> getEstadosClinica() {
        Map<String, EnumBase[]> enumMap =
                Map.of("EstadoGeneralAnimal", HistoriaClinica.EstadoGeneralAnimal.values(),
                        "ComportamientoAnimal", HistoriaClinica.ComportamientoAnimal.values(),
                        "CondicionMedicaAnimal", HistoriaClinica.CondicionMedicaAnimal.values(),
                        "NecesidadEspecialAnimal", HistoriaClinica.NecesidadEspecialAnimal.values());

        return enumMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Arrays.stream(entry.getValue())
                                .map(enumItem -> new EnumDto(enumItem.ordinal(), enumItem.normalName(), enumItem.getDescripcion()))
                                .toList()));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    protected static class EnumJson{
        private String nombre;
        private String descripcion;
        private int ordinal;
    }
}
