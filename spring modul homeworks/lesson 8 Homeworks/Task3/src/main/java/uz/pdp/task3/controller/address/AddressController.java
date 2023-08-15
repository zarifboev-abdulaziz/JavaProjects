package uz.pdp.task3.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.model.address.Address;
import uz.pdp.task3.model.address.District;
import uz.pdp.task3.payload.address.AddressDto;
import uz.pdp.task3.repository.address.AddressRepository;
import uz.pdp.task3.repository.address.DistrictRepository;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    @GetMapping("/byDistrict/{districtId}")
    public List<Address> getAllAddressByDistrict(@PathVariable Integer districtId){
        return addressRepository.getAllAddressByDistrictId(districtId);
    }

    @GetMapping("/{id}")
    public Address getOneAddress(@PathVariable Integer id){
        return addressRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Integer id){

        try {
            addressRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveAddress(@RequestBody AddressDto addressDto){
        District districtById = districtRepository.getById(addressDto.getDistrictId());

        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setDistrict(districtById);
        address.setStreet(addressDto.getStreet());

        addressRepository.save(address);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editAddress(@RequestBody AddressDto addressDto, @PathVariable Integer id){
        District districtById = districtRepository.getById(addressDto.getDistrictId());

        Address addressById = addressRepository.getById(id);

        addressById.setStreet(addressDto.getStreet());
        addressById.setDistrict(districtById);
        addressById.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(addressById);
        return "Successfully edited";
    }


}
