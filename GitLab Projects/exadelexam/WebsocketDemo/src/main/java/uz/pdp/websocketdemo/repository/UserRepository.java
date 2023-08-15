package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.websocketdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
