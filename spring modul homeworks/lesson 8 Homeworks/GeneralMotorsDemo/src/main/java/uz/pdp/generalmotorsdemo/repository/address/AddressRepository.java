package uz.pdp.generalmotorsdemo.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.address.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "select a.* from address a where a.district_id =:districtId", nativeQuery = true)
    List<Address> getAllAddressByDistrictId(Integer districtId);

}
