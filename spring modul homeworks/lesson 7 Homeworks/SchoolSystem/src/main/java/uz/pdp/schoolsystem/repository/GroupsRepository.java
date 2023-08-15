package uz.pdp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.schoolsystem.model.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {
}
