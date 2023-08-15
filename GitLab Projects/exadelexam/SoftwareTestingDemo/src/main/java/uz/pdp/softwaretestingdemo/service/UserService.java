package uz.pdp.softwaretestingdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.softwaretestingdemo.entity.User;
import uz.pdp.softwaretestingdemo.exception.BadRequestException;
import uz.pdp.softwaretestingdemo.exception.UserNotFoundException;
import uz.pdp.softwaretestingdemo.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //93 484 14 44
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> saveUser(User user) {
        boolean existsByUsername = userRepository.existsByUsername(user.getUsername());
        if (existsByUsername){
            throw new BadRequestException(user.getUsername() + " is already taken.");
        }

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> editUser(Integer userId, User user) {
        boolean byUsernameAndIdNot = userRepository.existsByUsernameAndIdNot(user.getUsername(), userId);
        if (byUsernameAndIdNot){
            throw new BadRequestException(user.getUsername() + " is already taken.");
        }

        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException("User with id: " + userId + " not found!");
        }

        User editingUser = userRepository.findById(userId).get();
        editingUser.setUsername(user.getUsername());
        editingUser.setName(user.getName());

        userRepository.save(editingUser);
        return ResponseEntity.ok(editingUser);
    }


    public ResponseEntity<?> deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException("User with id: " + userId + " not found!");
        }

        try {
            userRepository.deleteById(userId);
            return ResponseEntity.status(204).body("User successfully deleted");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(409).body("Error in deleting");
        }
    }
}
