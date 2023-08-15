package uz.pdp.springsecuritydemocrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String password;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> permissions = new HashSet<>();
        for (Role role : this.roles) {
            role.getPermissions().forEach(permission -> permissions.add(permission.getName()));
        }
        this.permissions.forEach(permission -> permissions.add(permission.getName()));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        permissions.forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission)));
        this.roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
