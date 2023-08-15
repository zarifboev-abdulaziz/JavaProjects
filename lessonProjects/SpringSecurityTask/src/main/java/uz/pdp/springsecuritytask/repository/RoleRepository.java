package uz.pdp.springsecuritytask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springsecuritytask.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
