package uz.pdp.softwaretestingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.softwaretestingdemo.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Integer id);
    Optional<User> findByUsername(String username);


}
