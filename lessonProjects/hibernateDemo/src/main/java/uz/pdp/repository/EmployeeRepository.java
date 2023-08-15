package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
