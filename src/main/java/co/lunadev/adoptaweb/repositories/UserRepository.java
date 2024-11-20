package co.lunadev.adoptaweb.repositories;


import co.lunadev.adoptaweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUsuarioByEmailIgnoreCase(String email);

}
