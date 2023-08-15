package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.model.Car;
import uz.pdp.task3.model.User;
import uz.pdp.task3.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/cars/{userId}")
    public List<Car> getAllUserCars(@PathVariable Integer userId){
        List<Car> allUserCars = userRepository.getAllUserCars(userId);

        return allUserCars;
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "Failed to delete";
        }
        return "Successfully deleted";
    }



}
