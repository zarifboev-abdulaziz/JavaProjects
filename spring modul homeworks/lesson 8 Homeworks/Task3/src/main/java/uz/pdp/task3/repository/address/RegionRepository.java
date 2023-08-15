package uz.pdp.task3.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.task3.model.address.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
