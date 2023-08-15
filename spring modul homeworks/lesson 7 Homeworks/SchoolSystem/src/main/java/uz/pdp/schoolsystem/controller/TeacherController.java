package uz.pdp.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.schoolsystem.model.Groups;
import uz.pdp.schoolsystem.model.Student;
import uz.pdp.schoolsystem.model.Subject;
import uz.pdp.schoolsystem.model.Teacher;
import uz.pdp.schoolsystem.payload.StudentDto;
import uz.pdp.schoolsystem.payload.TeacherDto;
import uz.pdp.schoolsystem.repository.SubjectRepository;
import uz.pdp.schoolsystem.repository.TeacherRepository;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getOneTeacher(@PathVariable Integer id){
        return teacherRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id){

        try {
            teacherRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveTeacher(@RequestBody TeacherDto  teacherDto){
        Subject subjectRepositoryById = subjectRepository.getById(teacherDto.getSubjectId());
        Teacher teacher = new Teacher();
        teacher.setAge(teacherDto.getAge());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setSubject(subjectRepositoryById);
        teacherRepository.save(teacher);
        return "Successfully Saved";
    }

    @PutMapping("/{id}")
    public String editTeacher(@RequestBody TeacherDto  teacherDto, @PathVariable Integer id){
        Teacher teacherRepositoryById = teacherRepository.getById(id);
        Subject subjectRepositoryById = subjectRepository.getById(teacherDto.getSubjectId());

        teacherRepositoryById.setAge(teacherDto.getAge());
        teacherRepositoryById.setFirstName(teacherDto.getFirstName());
        teacherRepositoryById.setLastName(teacherDto.getLastName());
        teacherRepositoryById.setSubject(subjectRepositoryById);
        teacherRepository.save(teacherRepositoryById);
        return "Successfully edited";
    }



}
