package uz.pdp.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uz.pdp.schoolsystem.model.Groups;
import uz.pdp.schoolsystem.model.School;
import uz.pdp.schoolsystem.payload.GroupDto;
import uz.pdp.schoolsystem.repository.GroupsRepository;
import uz.pdp.schoolsystem.repository.SchoolRepository;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    SchoolRepository schoolRepository;


    @GetMapping
    public List<Groups> getAllGroups(){
        return groupsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Groups getOneGroup(@PathVariable Integer id){
        return groupsRepository.findById(id).get();
    }

    @PostMapping()
    public String saveGroup(@RequestBody GroupDto groupDto){
        School school = schoolRepository.findById(groupDto.getSchoolId()).get();

        Groups groups = new Groups();
        groups.setName(groupDto.getGroupName());
        groups.setSchool(school);
        groupsRepository.save(groups);
        return "Successfully Saved";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id){
        try {
            groupsRepository.deleteById(id);
        }catch (Exception e){
            return "Process Failed";
        }
        return "Successfully deleted";
    }

    @PutMapping("/{id}")
    public String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto){
        School school = schoolRepository.findById(groupDto.getSchoolId()).get();
        Groups groups = groupsRepository.findById(id).get();

        groups.setSchool(school);
        groups.setName(groupDto.getGroupName());
        groupsRepository.save(groups);
        return "Successfully Edited";
    }



}
