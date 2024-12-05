package co.lunadev.adoptaweb.services;

import co.lunadev.adoptaweb.exceptions.ResourceNotFoundException;
import co.lunadev.adoptaweb.exceptions.UnknownException;
import co.lunadev.adoptaweb.exceptions.ValidationException;
import co.lunadev.adoptaweb.models.PeticionRegistro;
import co.lunadev.adoptaweb.models.Refugio;
import co.lunadev.adoptaweb.models.User;
import co.lunadev.adoptaweb.repositories.PeticionRegistroRepository;
import co.lunadev.adoptaweb.repositories.RefugioRepository;
import co.lunadev.adoptaweb.repositories.UserRepository;
import co.lunadev.adoptaweb.services.mail.DispatcherEmail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final PeticionRegistroRepository peticionRegistroRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RefugioRepository refugioRepository;
    private final DispatcherEmail dispatcherEmail;

    public AccountService(PeticionRegistroRepository peticionRegistroRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, RefugioRepository refugioRepository, DispatcherEmail dispatcherEmail) {
        this.peticionRegistroRepository = peticionRegistroRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.refugioRepository = refugioRepository;
        this.dispatcherEmail = dispatcherEmail;
    }

    @Transactional
    public void approveAccount(Long idPeticion){
        PeticionRegistro peticionRegistro = peticionRegistroRepository.findById(idPeticion).orElseThrow(()->new ResourceNotFoundException("Peticion"));

        if(peticionRegistro.isAprobado()){
            throw new ValidationException("La petición ya esta aprobada");
        }

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
        String tempPassword = "123456";
        newUser.setPassword(passwordEncoder.encode(tempPassword));
        newUser.setChangePassword(true);

        newRefugio.setUsuario(newUser);
        newUser.setRefugio(newRefugio);

        try {
            refugioRepository.save(newRefugio);
            userRepository.save(newUser);
            dispatcherEmail.accountApprovedEmail().send(newUser.getEmail(), newRefugio.getNombreRefugio(), tempPassword);
        } catch (Exception e) {
            throw new UnknownException("No se pudo agregar el usuario, "+e.getMessage());
        }
    }
}
