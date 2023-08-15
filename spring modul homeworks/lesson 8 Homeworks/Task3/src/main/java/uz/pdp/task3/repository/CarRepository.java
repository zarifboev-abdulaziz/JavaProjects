package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.task3.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("select c from Car c where c.region.id =:regionId")
    List<Car> getAllCarsByRegion(Integer regionId);
}
