package co.lunadev.adoptaweb.repositories.specifications;

import co.lunadev.adoptaweb.exceptions.ValidationException;
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

public class AnimalSpecification {

    private AnimalSpecification() {

    }

    public static Specification<Animal> animalSpecificationParamsPublicAllAnimal(Map<String, String> params) {
        return (root, query, cBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por municipio
            addPredicateIfValid(params, "mun", predicates, cBuilder, value -> {
                int mun = Integer.parseInt(value);
                Join<Animal, Refugio> refugioJoin = root.join("refugio", JoinType.INNER);
                return cBuilder.equal(refugioJoin.get("ubicacionMunicipio").get("id"), mun);
            });

            // Filtro por especie
            addPredicateIfValid(params, "especie", predicates, cBuilder, value -> {
                int especieId = Integer.parseInt(value);
                Join<Animal, Raza> razaJoin = root.join("raza", JoinType.INNER);
                return cBuilder.equal(razaJoin.get("especie").get("id"), especieId);
            });
            //considerar cambiar a una key de refugio numerica??
            addPredicateIfValid(params, "refugio", predicates, cBuilder, value -> {
                if(value.length() > 35){
                    throw new ValidationException("slug muy largo");
                }
                System.out.println("entro");
                Join<Animal, Refugio> refugioJoin = root.join("refugio", JoinType.INNER);
                return cBuilder.equal(refugioJoin.get("slug"), value);
            });
            query.distinct(true);
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

    public static Specification<Animal> habilitadoAdopcion(Boolean habilitado) {
        return ((root, query, cBuilder) ->
                cBuilder.and(cBuilder.equal(root.get("habilitadoAdopcion"), habilitado)));
    }

    public static Specification<Animal> filterByRefugioId(Long idRefugio) {
        return ((root, query, cBuilder) ->
                cBuilder.and(cBuilder.equal(root.get("refugio").get("id"), idRefugio)));
    }
    public static Specification<Animal> whereId(Long id) {
        return ((root, query, cBuilder) ->
                cBuilder.and(cBuilder.equal(root.get("id"), id)));
    }
    public static Specification<Animal> withRelations(boolean withRefugio) {
        return ((root, query, cBuilder) -> {
            root.fetch("historiaClinica", JoinType.INNER);
            root.fetch("raza", JoinType.INNER).fetch("especie", JoinType.INNER);
            if(withRefugio) {
                root.fetch("refugio", JoinType.INNER).
                        fetch("ubicacionMunicipio", JoinType.INNER).
                        fetch("departamento", JoinType.INNER);
            }
            root.fetch("fotoPortada", JoinType.LEFT);
            //query.distinct(true);
            //return cBuilder.conjunction();
            return null;
        }
        );
    }
    //TODO and refugio habilitado
}
