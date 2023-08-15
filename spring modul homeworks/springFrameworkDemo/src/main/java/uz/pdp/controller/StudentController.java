package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.model.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    List<Student> studentList = new ArrayList<>(Arrays.asList(
            new Student("Abror", 12, "12345"),
            new Student("Avaz", 17, "125678"),
            new Student("Aziz", 19, "1235656")
    ));

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String getStudentPage(Model model){
        model.addAttribute("students", studentList);
        return "student";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudentPage(Model model){
        return "addStudent";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public RedirectView addStudent(Model model, HttpServletRequest request){
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String phoneNumber = request.getParameter("phoneNumber");
        studentList.add(new Student(fullName, age, phoneNumber));

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/student");
        return redirectView;
    }

}
