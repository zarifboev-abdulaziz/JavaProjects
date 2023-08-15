package uz.pdp.hometask4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hometask4.model.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
}
