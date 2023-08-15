package uz.pdp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.schoolsystem.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
