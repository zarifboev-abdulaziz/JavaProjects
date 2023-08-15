package uz.pdp.generalmotorsdemo.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.address.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(value = "select d.* from district d where d.region_id =:regionId", nativeQuery = true)
    List<District> getAllDistrictByRegionId(Integer regionId);


}
