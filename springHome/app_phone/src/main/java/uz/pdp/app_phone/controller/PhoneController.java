package uz.pdp.app_phone.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.app_phone.model.Phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PhoneController {

    List<Phone> phoneList = new ArrayList<>(Arrays.asList(
            new Phone(1, "X", "12-56-35-76", 1234567),
            new Phone(2, "XX", "65-23-35-57", 125678),
            new Phone(3, "X MAX", "09-23-57-76", 126789877),
            new Phone(4, "Xs MAX", "12-89-56-76", 120987),
            new Phone(5, "X Pro", "90-23-35-76", 1234532427)
    ));


    //READ
    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public List<Phone> getPhoneList(){
        return phoneList;
    }

    //READ
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET)
    public Phone getPhoneById(@PathVariable Integer id){

        for (Phone phone : phoneList) {
            if (phone.getId() == id){
                return phone;
            }
        }
        return null;
    }

    //CREATE
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public String addPhone(@RequestBody Phone phone){

        for (Phone phone1 : phoneList) {
            if (phone1.getMacAddress().equals(phone.getMacAddress())){
                return "This MacAddress exists, Please enter another";
            }
        }

        phone.setId(phoneList.get(phoneList.size()-1).getId()+1);
        phoneList.add(phone);
        return "Successfully Added";
    }

    //UPDATE
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public String addPhone(@PathVariable Integer id, @RequestBody Phone inputPhone){
        boolean isEdited = false;

        for (Phone phone : phoneList) {
            if (phone.getId() == id){
                phone.setPhoneNumber(inputPhone.getPhoneNumber());
                phone.setMacAddress(inputPhone.getMacAddress());
                phone.setModel(inputPhone.getModel());
                isEdited = true;
            }
        }
        return isEdited?"Successfully edited":"Phone not Found";
    }

    //DELETE
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public String deletePhone(@PathVariable Integer id){

        boolean removeIf = phoneList.removeIf(phone -> phone.getId() == id);

        return removeIf?"Successfully Deleted":"Phone not Found";
    }

}
