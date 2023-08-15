package uz.pdp.regionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.regionservice.entity.Region;
import uz.pdp.regionservice.repository.RegionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@RestController

@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    public HttpEntity<?> getAllRegion() {
        return ResponseEntity.status(200).body(regionRepository.findAll());
    }

    @GetMapping("/by-country/{countryId}")
    public HttpEntity<?> getAllRegionByCountry(@PathVariable Integer countryId) {
        return ResponseEntity.status(200).body(regionRepository.findAllByCountryId(countryId));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getRegionById(@PathVariable Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            return ResponseEntity.status(200).body(optionalRegion.get());
        }

        return ResponseEntity.status(404).body("Error");
    }

    @PostMapping
    private HttpEntity<?> addRegion(@RequestBody Region region) {
        Region savedRegion = regionRepository.save(region);
        return ResponseEntity.status(200).body(savedRegion);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRegion(@PathVariable Integer id, @RequestBody Region region) {

        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (!optionalRegion.isPresent()) {
            return ResponseEntity.status(404).body("Not found");
        }
        Region editingRegion = optionalRegion.get();
        editingRegion.setName(region.getName());
        Region savedRegion = regionRepository.save(editingRegion);

        return ResponseEntity.status(200).body(savedRegion);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        try {
            regionRepository.deleteById(id);
            return ResponseEntity.status(200).body("Success");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(404).body("Error");
        }
    }
}
