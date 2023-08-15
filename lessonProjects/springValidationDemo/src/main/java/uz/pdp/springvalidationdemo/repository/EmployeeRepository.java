package uz.pdp.springvalidationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springvalidationdemo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
