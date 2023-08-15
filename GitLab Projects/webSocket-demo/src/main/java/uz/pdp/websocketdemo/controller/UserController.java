package uz.pdp.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User user){

        System.out.println(user);
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
        return ResponseEntity.ok(user);
    }

}
