package co.lunadev.adoptaweb.repositories.specifications;

import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.models.Raza;
import co.lunadev.adoptaweb.models.Refugio;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalSpecification {

    private AnimalSpecification() {

    }

    public static Specification<Animal> animalSpecificationParamsPublicAllAnimal(Map<String,String> params) {
        return ((root, query, cBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (params.containsKey("mun")) {
                int mun = Integer.parseInt(params.get("mun"));
                Join<Animal, Refugio> refugioJoin = root.join("refugio", JoinType.INNER);

                predicates.add(cBuilder.equal(refugioJoin.get("ubicacionMunicipio").get("id"), mun));
            }
            String especieParam = "especie";
            if (params.containsKey(especieParam) && !params.get(especieParam).isBlank()) {
                int especieId = Integer.parseInt(params.get(especieParam));
                Join<Animal, Raza> razaJoin = root.join("raza", JoinType.INNER);

                predicates.add(cBuilder.equal(razaJoin.get("especie").get("id"),especieId ));
            }
            return cBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
    public static Specification<Animal> withHabilitadoAdopcion(Boolean habilitado) {
        return ((root, query, cBuilder) ->
                cBuilder.and(cBuilder.equal(root.get("habilitadoAdopcion"), habilitado)));
    }
}
