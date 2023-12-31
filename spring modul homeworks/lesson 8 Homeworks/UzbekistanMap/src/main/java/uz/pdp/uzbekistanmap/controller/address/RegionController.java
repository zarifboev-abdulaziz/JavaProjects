package uz.pdp.uzbekistanmap.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.uzbekistanmap.model.address.Region;
import uz.pdp.uzbekistanmap.repository.address.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;


    @GetMapping
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Region getOneRegion(@PathVariable Integer id) {
        return regionRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteRegion(@PathVariable Integer id) {

        try {
            regionRepository.deleteById(id);
        } catch (Exception e) {
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveRegion(@RequestBody Region region) {
        regionRepository.save(region);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editRegion(@RequestBody Region region, @PathVariable Integer id) {
        Region regionById = regionRepository.getById(id);
        regionById.setName(region.getName());

        regionRepository.save(regionById);
        return "Successfully edited";
    }


}
