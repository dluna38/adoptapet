package co.lunadev.adoptaweb.repositories.specifications;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.models.Refugio;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RefugioSpecification {

    public static Specification<Refugio> refugioPublicParamsSpecification(Map<String, String> params) {
        return (root, query, cBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Filtro por municipio
            addPredicateIfValid(params, "mun", predicates, cBuilder, value -> {
                int mun = Integer.parseInt(value);
                return cBuilder.equal(root.get("ubicacionMunicipio").get("id"), mun);
            });
            return cBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void addPredicateIfValid(Map<String, String> params, String key, List<Predicate> predicates,
                                            CriteriaBuilder cBuilder, Function<String, Predicate> predicateFunction) {
        if (params.containsKey(key) && !params.get(key).isBlank()) {
            try {
                predicates.add(predicateFunction.apply(params.get(key)));
            } catch (NumberFormatException ignored) {
                // Ignoramos valores no válidos
            }
        }
    }

    public static Specification<Refugio> refugioHabilitado(boolean habilitado) {
        return (root, query, cBuilder) ->
                cBuilder.and(cBuilder.equal(root.get("habilitado"),habilitado));
    }
    public static Specification<Refugio> withMunicipioAndDepartamento() {
        return (root, query, cBuilder) ->{
            root.fetch("ubicacionMunicipio", JoinType.LEFT).fetch("departamento", JoinType.LEFT);
            return cBuilder.conjunction();
        };

    }
}
