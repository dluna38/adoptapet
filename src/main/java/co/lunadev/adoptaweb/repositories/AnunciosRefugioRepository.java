package co.lunadev.adoptaweb.repositories;

import co.lunadev.adoptaweb.controllers.response.PageResponse;
import co.lunadev.adoptaweb.models.AnuncioRefugio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnunciosRefugioRepository extends JpaRepository<AnuncioRefugio, Long> {

    Page<AnuncioRefugio> findAll(Pageable pageable);

    Page<AnuncioRefugio> findAllByRefugioId(Long refugioId,Pageable pageable);

    Page<AnuncioRefugio> findByRefugio_IdAndPublicado(Long id, Boolean publicado, Pageable pageable);

    Page<AnuncioRefugio> findAllByRefugio_Id(Long refugioId, Pageable pageable);

    Page<AnuncioRefugio> findAllByRefugio_Slug(String slug, Pageable pageable);
}
