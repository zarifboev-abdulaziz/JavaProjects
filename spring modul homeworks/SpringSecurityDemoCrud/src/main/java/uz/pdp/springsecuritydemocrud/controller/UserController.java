package uz.pdp.springsecuritydemocrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecuritydemocrud.entity.Permission;
import uz.pdp.springsecuritydemocrud.entity.Product;
import uz.pdp.springsecuritydemocrud.entity.Role;
import uz.pdp.springsecuritydemocrud.entity.User;
import uz.pdp.springsecuritydemocrud.payload.RoleUserDto;
import uz.pdp.springsecuritydemocrud.repository.PermissionRepository;
import uz.pdp.springsecuritydemocrud.repository.RoleRepository;
import uz.pdp.springsecuritydemocrud.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping()
    public HttpEntity<?> addRoleToUser(@RequestBody RoleUserDto roleUserDto){
        Optional<User> optionalUser = userRepository.findById(roleUserDto.getUserId());
        if (!optionalUser.isPresent()) return ResponseEntity.status(404).body("User not found");
        Optional<Role> optionalRole = roleRepository.findById(roleUserDto.getRoleId());
        if (!optionalRole.isPresent()) return ResponseEntity.status(404).body("Role Not Found");

        Set<Permission> permissionSet = new HashSet<>();
        if (roleUserDto.getPermissionIds().size() != 0){
            for (Integer permissionId : roleUserDto.getPermissionIds()) {
                Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);
                if (!optionalPermission.isPresent()) return ResponseEntity.status(404).body("Permission with id: " + permissionId + " not found");
                permissionSet.add(optionalPermission.get());
            }
        }

        User user = optionalUser.get();
        user.getRoles().add(optionalRole.get());
        user.getPermissions().addAll(permissionSet);
        userRepository.save(user);
        return ResponseEntity.status(200).body("Successful operation");
    }


}
