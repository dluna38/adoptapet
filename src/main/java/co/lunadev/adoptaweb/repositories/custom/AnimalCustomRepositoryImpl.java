package co.lunadev.adoptaweb.repositories.custom;

import co.lunadev.adoptaweb.models.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AnimalCustomRepositoryImpl implements AnimalCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Animal> findAllCustom(Specification<Animal> specification, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<Animal> query = cb.createQuery(Animal.class);
        Root<Animal> root = query.from(Animal.class);

        if (pageable.getSort().isSorted()) {
            pageable.getSort().forEach(order -> {
                query.orderBy(order.isAscending() ? cb.asc(root.get(order.getProperty())) : cb.desc(root.get(order.getProperty())));
            });
        }
        query.where(specification.toPredicate(root,query,cb));
        query.select(root);
        final Long total = session.createQuery( query.createCountQuery() )
                .getSingleResult();

        List<Animal> animals = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset()) // Índice inicial
                .setMaxResults(pageable.getPageSize())      // Tamaño de la página
                .getResultList();

        return new PageImpl<>(animals, pageable, total);
    }

    @Override
    public Optional<Animal> findCustomById(Specification<Animal> specification, Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> query = cb.createQuery(Animal.class);
        Root<Animal> root = query.from(Animal.class);

        query.where(specification.toPredicate(root,query,cb));

        query.select(root);
        try {
            return Optional.of(entityManager.createQuery(query).getSingleResult());
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty(); // Return an empty Optional if no result
        }
    }


}
