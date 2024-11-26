package co.lunadev.adoptaweb.services;

import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.models.PeticionRegistro;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.repositories.PeticionRegistroRepository;
import co.lunadev.adoptaweb.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private PeticionRegistroRepository peticionRegistroRepository;
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public AccountService(PeticionRegistroRepository peticionRegistroRepository, PasswordEncoder passwordEncoder) {
        this.peticionRegistroRepository = peticionRegistroRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void approveAccount(Long idPeticion){
        PeticionRegistro peticionRegistro = peticionRegistroRepository.findById(idPeticion).orElseThrow(()->new ResourceNotFoundException("Peticion"));
        peticionRegistro.setAprobado(true);
        Refugio newRefugio = new Refugio();
        newRefugio.setCorreo(peticionRegistro.getCorreo());
        newRefugio.setNombreRefugio(peticionRegistro.getNombreRefugio());
        newRefugio.setNombreEncargado(peticionRegistro.getNombreEncargado());
        newRefugio.setUbicacionMunicipio(peticionRegistro.getUbicacionMunicipio());
        newRefugio.setDescripcion(peticionRegistro.getDescripcion());
        newRefugio.setTelefono(peticionRegistro.getTelefono());

        User newUser = new User();
        newUser.setEmail(newRefugio.getCorreo());
        newUser.setRole(User.Rol.ROLE_USUARIO);
        newUser.setPassword(passwordEncoder.encode("123"));
        newUser.setChangePassword(true);

        newRefugio.setUsuario(newUser);
        newUser.setRefugio(newRefugio);

        userRepository.save(newUser);
    }
}
