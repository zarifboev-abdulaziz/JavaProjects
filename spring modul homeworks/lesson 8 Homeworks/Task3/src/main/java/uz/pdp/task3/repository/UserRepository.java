package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.task3.model.Car;
import uz.pdp.task3.model.User;

import javax.jnlp.IntegrationService;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query(value = "select c.* \n" +
//            "from users_cars uc\n" +
//            "         join car c on c.id = uc.cars_id\n" +
//            "where uc.users_id =:userId", nativeQuery = true)
//    List<Car> getAllUserCars(Integer userId);


    @Query("select u.cars from users u where u.id =:userId")
    List<Car> getAllUserCars(Integer userId);

}
