package uz.pdp.softwaretestingdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.softwaretestingdemo.entity.User;
import uz.pdp.softwaretestingdemo.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Integer userId, @RequestBody User user){
        return userService.editUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }


}
