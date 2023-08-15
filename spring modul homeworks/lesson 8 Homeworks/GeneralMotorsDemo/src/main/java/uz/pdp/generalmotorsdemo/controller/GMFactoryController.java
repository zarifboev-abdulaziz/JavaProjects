package uz.pdp.generalmotorsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.generalmotorsdemo.model.GMFactory;
import uz.pdp.generalmotorsdemo.model.address.Region;
import uz.pdp.generalmotorsdemo.repository.GMFactoryRepository;

import java.util.List;

@RestController
@RequestMapping("/gmFactory")
public class GMFactoryController {
    @Autowired
    GMFactoryRepository gmFactoryRepository;

    @GetMapping
    public List<GMFactory> getAllGMFactory() {
        return gmFactoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public GMFactory getOneGMFactory(@PathVariable Integer id) {
        return gmFactoryRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteGMFactory(@PathVariable Integer id) {

        try {
            gmFactoryRepository.deleteById(id);
        } catch (Exception e) {
            return "Failed to delete";
        }
        return "Successfully deleted";
    }


}
