package uz.pdp.continentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.continentservice.entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {

}
