package uz.pdp.task3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.model.Car;
import uz.pdp.task3.model.address.District;
import uz.pdp.task3.repository.CarRepository;

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

    @GetMapping("/byRegion/{regionId}")
    public List<Car> getAllCarByRegionId(@PathVariable Integer regionId){
        return carRepository
                .getAllCarsByRegion(regionId);
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



}
