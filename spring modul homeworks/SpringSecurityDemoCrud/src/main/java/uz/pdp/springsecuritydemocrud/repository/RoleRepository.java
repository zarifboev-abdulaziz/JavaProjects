package uz.pdp.springsecuritydemocrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemocrud.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
