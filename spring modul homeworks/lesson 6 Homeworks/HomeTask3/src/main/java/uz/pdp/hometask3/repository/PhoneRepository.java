package uz.pdp.hometask3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hometask3.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
