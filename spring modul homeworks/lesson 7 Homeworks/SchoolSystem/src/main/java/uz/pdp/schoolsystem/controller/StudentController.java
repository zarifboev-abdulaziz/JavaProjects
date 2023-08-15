package uz.pdp.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.schoolsystem.model.Groups;
import uz.pdp.schoolsystem.model.Student;
import uz.pdp.schoolsystem.payload.StudentDto;
import uz.pdp.schoolsystem.repository.GroupsRepository;
import uz.pdp.schoolsystem.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupsRepository groupsRepository;

    @GetMapping
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getOneStudent(@PathVariable Integer id){
        return studentRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){

        try {
            studentRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveStudent(@RequestBody StudentDto studentDto){
        Groups groups = groupsRepository.findById(studentDto.getGroupId()).get();
        Student student = new Student();
        student.setAge(studentDto.getAge());
        student.setGroup(groups);
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        studentRepository.save(student);
        return "Successfully Saved";
    }

    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Groups groups = groupsRepository.getById(id);
        Student byId = studentRepository.getById(id);
        byId.setLastName(studentDto.getLastName());
        byId.setGroup(groups);
        byId.setFirstName(studentDto.getFirstName());
        byId.setAge(studentDto.getAge());

        studentRepository.save(byId);
        return "Successfully Edited";
    }


}
