package uz.pdp.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hometask.model.Course;
import uz.pdp.hometask.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    //Get All Courses
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public List<Course> getCourses(){
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    //Get Course By id
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Integer id){
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return course;
        }

        return null;
    }

    //CREATE
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public String addCourse(@RequestBody Course course){
        courseRepository.save(course);
        return "Successfully saved";
    }

    //DELETE
    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public String deleteCourse(@PathVariable Integer id){
        courseRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
    public String editCourse(@PathVariable Integer id, @RequestBody Course course){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course editingCourse = optionalCourse.get();
            editingCourse.setActive(course.isActive());
            editingCourse.setName(course.getName());
            courseRepository.save(editingCourse);
            return "Successfully edited";
        }
        return "Process is Failed";
    }

}
