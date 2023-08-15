package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.EmployeeDao;
import uz.pdp.model.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    EmployeeDao employeeDao;


    @Transactional
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }
}
