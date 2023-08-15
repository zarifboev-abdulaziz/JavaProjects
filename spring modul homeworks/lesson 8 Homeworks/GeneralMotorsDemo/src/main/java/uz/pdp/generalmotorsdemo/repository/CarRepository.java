package uz.pdp.generalmotorsdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "select c.*\n" +
            "from auto_shop_cars ac\n" +
            "join car c on c.id = ac.cars_id\n" +
            "where ac.auto_shop_id =:autoShopId", nativeQuery = true)
    List<Car> getAllCarByAutoShopId(Integer autoShopId);
}
