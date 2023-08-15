package uz.pdp.springvalidationdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springvalidationdemo.model.Employee;
import uz.pdp.springvalidationdemo.repository.EmployeeRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    //READ
    @GetMapping("/show")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "showEmployees";
    }

    @GetMapping("/add")
    public String getAddForm(@ModelAttribute("employee") Employee employee) {
        return "addEmployeeForm";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addEmployeeForm";
        }

        employeeRepository.save(employee);
        return "redirect:/employee/show";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid Employee employee,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "updateEmployee";
        }

        employeeRepository.save(employee);
        return "redirect:/employee/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        try {
            employeeRepository.deleteById(id);
        }catch (Exception e){

        }

        return "redirect:/employee/show";
    }


}
