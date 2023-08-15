package uz.pdp.generalmotorsdemo.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.address.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
