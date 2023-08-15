package uz.pdp.generalmotorsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.generalmotorsdemo.model.Car;
import uz.pdp.generalmotorsdemo.model.address.Region;
import uz.pdp.generalmotorsdemo.repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @GetMapping("/byAutoShopId/{autoShopId}")
    public List<Car> getAllCarByAutoShop(@PathVariable Integer autoShopId) {
        return carRepository.getAllCarByAutoShopId(autoShopId);
    }

    @GetMapping("/{id}")
    public Car getOneCar(@PathVariable Integer id) {
        return carRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Integer id) {

        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveCar(@RequestBody Car car) {
        carRepository.save(car);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editCar(@RequestBody Car car, @PathVariable Integer id) {
        Car carRepositoryById = carRepository.getById(id);
        carRepositoryById.setModel(car.getModel());
        carRepositoryById.setPrice(car.getPrice());
        carRepositoryById.setYear(car.getYear());

        carRepository.save(carRepositoryById);
        return "Successfully edited";
    }



}
