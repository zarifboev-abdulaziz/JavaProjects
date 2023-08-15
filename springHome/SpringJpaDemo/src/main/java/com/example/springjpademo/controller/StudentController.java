package com.example.springjpademo.controller;

import com.example.springjpademo.model.Student;
import com.example.springjpademo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    //Get All Students
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    //Get student By id
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            return student;
        }

        return null;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String getStudents(@RequestBody Student student){
        studentRepository.save(student);
        return "Successfully saved";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String getStudents(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student editingStudent = optionalStudent.get();
            editingStudent.setFirstName(student.getFirstName());
            editingStudent.setLastName(student.getLastName());
            editingStudent.setPhoneNumber(student.getPhoneNumber());
            studentRepository.save(editingStudent);
            return "Successfully edited";
        }
        return "Process is Failed";
    }

}
