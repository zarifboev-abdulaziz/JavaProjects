package uz.pdp.hometask3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hometask3.model.Phone;
import uz.pdp.hometask3.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public List<Phone> getPhones(){
        return phoneRepository.findAll();
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET)
    public Phone getPhone(@PathVariable Integer id){
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        return optionalPhone.orElse(null);
    }

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public String addPhones(@RequestBody Phone phone){
        for (Phone phone1 : phoneRepository.findAll()) {
            if (phone1.getMacAddress().equals(phone.getMacAddress())){
                return "This Mac Address is already exists";
            }
        }

        phoneRepository.save(phone);
        return "Successfully Saved";
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public String editPhone(@PathVariable Integer id, @RequestBody Phone phone){
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if (optionalPhone.isPresent()) {
            Phone editingPhone = optionalPhone.get();
            editingPhone.setInfo(phone.getInfo());
            editingPhone.setMacAddress(phone.getMacAddress());
            editingPhone.setName(phone.getName());
            editingPhone.setModel(phone.getModel());

            phoneRepository.save(editingPhone);
            return "Successfully Edited";
        }

        return "Operation Failed";
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public String deletePhone(@PathVariable Integer id){
        boolean isDeleted = false;
        for (Phone phone1 : phoneRepository.findAll()) {
            if (phone1.getId() == id){
                isDeleted = true;
                return "This Mac Address is already exists";
            }
        }

        if (isDeleted) {
            phoneRepository.deleteById(id);
            return "Successfully Deleted";
        } else {
            return "Operation Failed";
        }
    }


}
