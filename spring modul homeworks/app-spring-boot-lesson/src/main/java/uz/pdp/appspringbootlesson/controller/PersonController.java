package uz.pdp.appspringbootlesson.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringbootlesson.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {

    List<Student> studentList = new ArrayList<>(Arrays.asList(
       new Student(1, "Abror", "Abrorov", new Date(), "90123456"),
       new Student(2, "Avaz", "Avazov", new Date(), "91123456"),
       new Student(3, "Eldor", "Eldorov", new Date(), "92123456"),
       new Student(4, "Aziz", "Azizov", new Date(), "93123456")
    ));

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentList;
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Integer id){
        for (Student student : studentList) {
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student){
        student.setId(studentList.get(studentList.size()-1).getId() + 1);
        studentList.add(student);

        return "Successfully added";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id){

        boolean removeIf = studentList.removeIf(student -> student.getId() == id);

        return removeIf?"Successfully deleted":"Student not found";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody Student inputStudent){
        boolean isEdited = false;

        for (Student student : studentList) {
            if (student.getId() == id){
                student.setFirstName(inputStudent.getFirstName());
                student.setLastName(inputStudent.getLastName());
                student.setBirthDate(inputStudent.getBirthDate());
                student.setPhoneNumber(inputStudent.getPhoneNumber());
                isEdited = true;
            }
        }

        return isEdited?"Successfully edited":"Student not found";
    }

}
