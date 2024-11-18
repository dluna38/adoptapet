package co.lunadev.adoptaweb.repositories;


import co.lunadev.adoptaweb.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Short> {

    Optional<Usuario> findUsuarioByCorreoIgnoreCase(String correo);

}
