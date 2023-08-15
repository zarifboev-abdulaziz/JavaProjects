package uz.pdp.continentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.continentservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
