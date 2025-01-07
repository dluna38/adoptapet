package co.lunadev.adoptaweb.repositories.custom;

import co.lunadev.adoptaweb.models.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalCustomRepository {

    Page<Animal> findAllCustom(Specification<Animal> specification, Pageable pageable);
}
