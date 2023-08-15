package uz.pdp.countryservice.controller;

import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.pdp.countryservice.dto.ContinentDto;
import uz.pdp.countryservice.dto.CountryDto;
import uz.pdp.countryservice.dto.RegionDto;
import uz.pdp.countryservice.entity.Country;
import uz.pdp.countryservice.repository.CountryRepository;

import java.util.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public HttpEntity<?> getAllCountry() {
        List<Country> countryList = countryRepository.findAll();
        String continentUrl = "http://CONTINENT/api/continent";

        List<CountryDto> countryDtoList = new ArrayList<>();
        for (Country country : countryList) {
            ContinentDto continentDto = restTemplate.getForObject(continentUrl + "/" +  country.getContinentId(), ContinentDto.class);
            if (continentDto == null) continue;
            CountryDto countryDto = new CountryDto(country.getId(), country.getName(), continentDto.getName());
            countryDtoList.add(countryDto);
        }

        return ResponseEntity.status(200).body(countryDtoList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCountryById(@PathVariable Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            return ResponseEntity.status(200).body(optionalCountry.get());
        }

        return ResponseEntity.status(404).body("Error");
    }

    @GetMapping("/regions/{countryId}")
    public HttpEntity<?> getCountryWithRegionsById(@PathVariable Integer countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (!optionalCountry.isPresent()) {
            return ResponseEntity.status(404).body(new Country());
        }

        RegionDto[] regionDtos = restTemplate.getForObject("http://REGION/api/region/by-country/" + countryId, RegionDto[].class);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", optionalCountry.get().getId());
        objectMap.put("name", optionalCountry.get().getName());
        objectMap.put("regions", regionDtos);

        return ResponseEntity.status(200).body(objectMap);
    }


    @PostMapping
    private HttpEntity<?> addCountry(@RequestBody Country country) {
        Country savedCountry = countryRepository.save(country);
        return ResponseEntity.status(200).body(savedCountry);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCountry(@PathVariable Integer id, @RequestBody Country country) {

        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (!optionalCountry.isPresent()) {
            return ResponseEntity.status(404).body("Not found");
        }
        Country editingCountry = optionalCountry.get();
        editingCountry.setName(country.getName());
        Country savedCountry = countryRepository.save(editingCountry);

        return ResponseEntity.status(200).body(savedCountry);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        try {
            countryRepository.deleteById(id);
            return ResponseEntity.status(200).body("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Error");
        }
    }
}
