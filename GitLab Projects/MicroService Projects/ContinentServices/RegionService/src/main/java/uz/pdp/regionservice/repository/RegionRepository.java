package uz.pdp.regionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.regionservice.entity.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findAllByCountryId(Integer countryId);

}
