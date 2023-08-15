package uz.pdp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.schoolsystem.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
