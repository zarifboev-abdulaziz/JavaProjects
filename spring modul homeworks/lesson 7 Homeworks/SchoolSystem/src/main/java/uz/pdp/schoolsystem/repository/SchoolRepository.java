package uz.pdp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.schoolsystem.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
