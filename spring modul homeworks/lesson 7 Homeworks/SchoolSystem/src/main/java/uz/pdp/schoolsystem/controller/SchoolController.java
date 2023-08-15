package uz.pdp.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.schoolsystem.model.School;
import uz.pdp.schoolsystem.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepository;

    //Get All Students
    @GetMapping
    public List<School> getSchools(){
        List<School> all = schoolRepository.findAll();
        return all;
    }

    //Get student By id
    @GetMapping(value = "/{id}")
    public School getSchool(@PathVariable Integer id){
        Optional<School> byId = schoolRepository.findById(id);
        if (byId.isPresent()) {
            School school = byId.get();
            return school;
        }

        return null;
    }

    @PostMapping
    public String saveSchool(@RequestBody School school){
        schoolRepository.save(school);
        return "Successfully saved";
    }

    @DeleteMapping("/{id}")
    public String deleteSchool(@PathVariable Integer id){
        try {
            schoolRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully Deleted";
    }

    @PutMapping("/{id}")
    public String editSchool(@PathVariable Integer id, @RequestBody School school){
        Optional<School> byId = schoolRepository.findById(id);
        if (byId.isPresent()) {
            School school1 = byId.get();
            school1.setName(school.getName());
            schoolRepository.save(school1);
            return "Successfully edited";
        }
        return "Process is Failed";

    }



}
