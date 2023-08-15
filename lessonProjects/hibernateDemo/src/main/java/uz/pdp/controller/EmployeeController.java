package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.dao.EmployeeDao;
import uz.pdp.model.Employee;
import uz.pdp.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/show")
public class EmployeeController {
    @Autowired
    CourseService courseService;

    @GetMapping
    public String getEmployee(Model model) {
        List<Employee> employees = courseService.getEmployees();


        model.addAttribute("employees", employees);
        return "views/employees";
    }

}
