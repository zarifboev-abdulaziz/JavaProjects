package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.websocketdemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
