package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.model.Course;
import uz.pdp.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CourseController {

    List<Course> courseList =  new ArrayList<>(Arrays.asList(
            new Course(1, "Java", "Java basics and OOP"),
            new Course(2, "Python", "Python Advanced"),
            new Course(3, "C++", "C++ Foundation"),
            new Course(4, "Android", "Android Studio"),
            new Course(5, "PHP", "PHP Web Development")
    ));

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String getStudentPage(Model model){
        model.addAttribute("courses", courseList);
        return "course";
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addStudentPage(Model model){
        return "addCourse";
    }

    //  courseNumber, courseName, courseInfo
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public RedirectView addStudent(Model model, HttpServletRequest request){
        int courseNumber = Integer.parseInt(request.getParameter("courseNumber"));
        String courseName = request.getParameter("courseName");
        String courseInfo = request.getParameter("courseInfo");

        courseList.add(new Course(courseNumber, courseName, courseInfo));

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/course");
        return redirectView;
    }


}
