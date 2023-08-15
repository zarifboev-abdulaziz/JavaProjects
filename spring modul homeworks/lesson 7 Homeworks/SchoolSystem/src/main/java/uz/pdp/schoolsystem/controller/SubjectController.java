package uz.pdp.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.schoolsystem.model.School;
import uz.pdp.schoolsystem.model.Student;
import uz.pdp.schoolsystem.model.Subject;
import uz.pdp.schoolsystem.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subject getOneSubject(@PathVariable Integer id){
        return subjectRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id){

        try {
            subjectRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveSubject(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Subject subjectRepositoryById = subjectRepository.getById(id);
        subjectRepositoryById.setName(subject.getName());

        subjectRepository.save(subjectRepositoryById);
        return "Successfully edited";

    }



}
