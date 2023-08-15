package uz.pdp.amigoscodejsonwebtoken.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.amigoscodejsonwebtoken.domain.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
