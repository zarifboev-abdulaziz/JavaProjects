package uz.pdp.hometask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hometask.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
