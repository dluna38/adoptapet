package co.lunadev.adoptaweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false,length = 100,unique = true)
    private String correo;

    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol role;

    public Usuario(String correo) {
        this.correo = correo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public enum Rol {
        ROLE_SUPER_ADMIN,
        ROLE_ADMIN,
        ROLE_USUARIO
    }
}
