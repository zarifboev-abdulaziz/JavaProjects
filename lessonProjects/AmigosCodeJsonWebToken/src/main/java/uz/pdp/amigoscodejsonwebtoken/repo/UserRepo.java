package uz.pdp.amigoscodejsonwebtoken.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.amigoscodejsonwebtoken.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
