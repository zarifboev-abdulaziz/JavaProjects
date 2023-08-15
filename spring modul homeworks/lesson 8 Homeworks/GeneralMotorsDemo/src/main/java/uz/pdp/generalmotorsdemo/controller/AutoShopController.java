package uz.pdp.generalmotorsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.generalmotorsdemo.model.AutoShop;
import uz.pdp.generalmotorsdemo.model.address.Region;
import uz.pdp.generalmotorsdemo.repository.AutoShopRepository;

import java.util.List;

@RestController
@RequestMapping("/autoShop")
public class AutoShopController {
    @Autowired
    AutoShopRepository autoShopRepository;


    @GetMapping
    public List<AutoShop> getAllAutoShop() {
        return autoShopRepository.findAll();
    }

    @GetMapping("/{id}")
    public AutoShop getOneAutoShop(@PathVariable Integer id) {
        return autoShopRepository.findById(id).get();
    }

    @GetMapping("/byGMFactory/{gmFactoryId}")
    public List<AutoShop> getAllAutoShopByGMFactory(@PathVariable Integer gmFactoryId) {
        return autoShopRepository.getAllAutoShopByGMFactory(gmFactoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteAutoShop(@PathVariable Integer id) {

        try {
            autoShopRepository.deleteById(id);
        } catch (Exception e) {
            return "Failed to delete";
        }
        return "Successfully deleted";
    }


}
