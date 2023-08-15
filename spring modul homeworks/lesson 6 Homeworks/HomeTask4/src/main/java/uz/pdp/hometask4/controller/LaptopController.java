package uz.pdp.hometask4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hometask4.model.Laptop;
import uz.pdp.hometask4.repository.LaptopRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    @Autowired
    LaptopRepository laptopRepository;

    //Get All Students
    @RequestMapping(value = "/laptop", method = RequestMethod.GET)
    public List<Laptop> getLaptops(){
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops;
    }

    //Get student By id
    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.GET)
    public Laptop getLaptop(@PathVariable Integer id){
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);

        if (optionalLaptop.isPresent()){
            Laptop laptop = optionalLaptop.get();
            return laptop;
        }

        return null;
    }

    @RequestMapping(value = "/laptop", method = RequestMethod.POST)
    public String getStudents(@RequestBody Laptop laptop){
        laptopRepository.save(laptop);
        return "Successfully saved";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteLaptop(@PathVariable Integer id){
        laptopRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.PUT)
    public String editLaptop(@PathVariable Integer id, @RequestBody Laptop laptop){
        Optional<Laptop> optionaLaptop = laptopRepository.findById(id);
        if (optionaLaptop.isPresent()) {
            Laptop editingLaptop = optionaLaptop.get();
            editingLaptop.setBrand_name(laptop.getBrand_name());
            editingLaptop.setMacAddress(laptop.getMacAddress());
            editingLaptop.setModel(laptop.getModel());
            editingLaptop.setName(laptop.getName());
            editingLaptop.setRam(laptop.getRam());
            editingLaptop.setStorage(laptop.getStorage());
            laptopRepository.save(editingLaptop);
            return "Successfully edited";
        }
        return "Process is Failed";
    }

}
