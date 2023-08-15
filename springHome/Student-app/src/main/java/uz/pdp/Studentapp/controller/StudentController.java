package uz.pdp.Studentapp.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Studentapp.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList = new ArrayList<>(Arrays.asList(
            new Student(1, "Abror", "90123456", "Java"),
            new Student(2, "Javohir", "91123456", "Python"),
            new Student(3, "Olmos", "92123456", "C++"),
            new Student(4, "Karim", "93123456", "Android"),
            new Student(5, "Dilshod", "94123456", "Flutter")
    ));

    //READ
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentList;
    }

    //READ BY ID
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudentsById(@PathVariable Integer id){
        for (Student student : studentList) {
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    //CREATE
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student inputStudent){
        for (Student student1 : studentList) {
            if (student1.getPhoneNumber().equals(inputStudent.getPhoneNumber())){
                return "this phone number exists, Please another phone number";
            }
        }

        inputStudent.setId(studentList.get(studentList.size()-1).getId()+1);
        studentList.add(inputStudent);

        return "Successfully Added to the list";
    }

    //UPDATE
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id ,@RequestBody Student inputStudent){
        boolean isEdited = false;

        for (Student student : studentList) {
            if (student.getId() == id){
                student.setName(inputStudent.getName());
                student.setPhoneNumber(inputStudent.getPhoneNumber());
                student.setCourseName(inputStudent.getCourseName());
                isEdited = true;
            }
        }

        return isEdited?"Successfully Edited":"User Not Found";
    }

    //DELETE
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id){

        boolean removeIf = studentList.removeIf(student -> student.getId() == id);

        return removeIf?"Successfully Removed":"User Not Found";
    }







}
