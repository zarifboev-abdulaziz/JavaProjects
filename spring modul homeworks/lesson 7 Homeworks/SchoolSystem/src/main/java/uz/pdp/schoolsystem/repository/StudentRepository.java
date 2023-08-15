package uz.pdp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.schoolsystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
