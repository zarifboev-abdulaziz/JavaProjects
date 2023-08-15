package uz.pdp.lesson2spring.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson2spring.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController {
    List<Person> personList = new ArrayList<>(Arrays.asList(
            new Person(1, "Abdulaziz", "993885321"),
            new Person(2, "Nodirbek", "993852351")
    ));

    @GetMapping
    public List<Person> getPersons(){
        return personList;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id){
        for (Person person : personList) {
            if (person.getId() == id){
                return person;
            }
        }
        return null;
    }


    @PostMapping
    public String addPerson(@RequestBody Person person){
        person.setId(personList.get(personList.size()-1).getId() + 1);
        personList.add(person);

        return "Successfully Saved";
    }

    @PutMapping("/{personId}")
    public String editPerson(@PathVariable Integer personId, @RequestBody Person inputPerson){
        boolean isEdited = false;
        for (Person person1 : personList) {
            if (person1.getId()==personId) {
                person1.setFullName(inputPerson.getFullName());
                person1.setPhoneNumber(inputPerson.getPhoneNumber());
                isEdited = true;
            }
        }
        return isEdited?"Successfully Edited":"Person not found";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id){
        boolean isDeleted = false;
        for (Person person : personList) {
            if (person.getId() == id){
                personList.remove(person);
                isDeleted = true;
                break;
            }
        }

        return isDeleted?"Successfully Deleted":"Person not found";
    }
}
