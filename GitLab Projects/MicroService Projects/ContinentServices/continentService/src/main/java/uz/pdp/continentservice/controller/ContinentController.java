package uz.pdp.continentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.continentservice.entity.Continent;
import uz.pdp.continentservice.repository.ContinentRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/continent")
public class ContinentController {

    @Autowired
    ContinentRepository continentRepository;

    @GetMapping
    public HttpEntity<?> getAllContinent() {
        return ResponseEntity.status(200).body(continentRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getContinentById(@PathVariable Integer id) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (optionalContinent.isPresent()) {
            return ResponseEntity.status(200).body(optionalContinent.get());
        }

        return ResponseEntity.status(404).body("Error");
    }

    @PostMapping
    private HttpEntity<?> addContinent(@RequestBody Continent continent) {
        Continent savedContinent = continentRepository.save(continent);
        return ResponseEntity.status(200).body(savedContinent);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editContinent(@PathVariable Integer id, @RequestBody Continent continent) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (!optionalContinent.isPresent()) {
            return ResponseEntity.status(404).body("Not found");
        }
        Continent editingContinent = optionalContinent.get();
        editingContinent.setName(continent.getName());
        Continent savedContinent = continentRepository.save(editingContinent);

        return ResponseEntity.status(200).body(savedContinent);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        try {
            continentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Success");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(404).body("Error");
        }
    }

}
