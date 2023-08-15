package uz.pdp.springsecuritydemocrud.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springsecuritydemocrud.entity.Permission;
import uz.pdp.springsecuritydemocrud.entity.Role;
import uz.pdp.springsecuritydemocrud.entity.User;
import uz.pdp.springsecuritydemocrud.repository.ProductRepository;
import uz.pdp.springsecuritydemocrud.repository.PermissionRepository;
import uz.pdp.springsecuritydemocrud.repository.RoleRepository;
import uz.pdp.springsecuritydemocrud.repository.UserRepository;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    public String initMode;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final ProductRepository bookRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("never")) {
            return;
        }


        Permission view_products = permissionRepository.save(new Permission(null, "VIEW_PRODUCTS"));
        Permission add_product = permissionRepository.save(new Permission(null, "ADD_PRODUCT"));
        Permission edit_product = permissionRepository.save(new Permission(null, "EDIT_PRODUCT"));
        Permission delete_product = permissionRepository.save(new Permission(null, "DELETE_PRODUCT"));


        Role roleAdmin = roleRepository.save(new Role(null, "ROLE_ADMIN", new HashSet<>(Arrays.asList(view_products, add_product))));

        Role roleUser = roleRepository.save(new Role(null, "ROLE_USER", new HashSet<>(Arrays.asList(view_products))));

        User admin1 = userRepository.save(new User(null, "Admin1", "admin1@gmail.com", passwordEncoder.encode("123"),
                new HashSet<>(Collections.singletonList(roleAdmin)), new HashSet<>(Arrays.asList(edit_product, delete_product))));

        User admin2 = userRepository.save(new User(null, "Admin2", "admin2@gmail.com", passwordEncoder.encode("123"),
                new HashSet<>(Collections.singletonList(roleAdmin)), new HashSet<>()));

        User user1 = userRepository.save(new User(null, "User", "user@gmail.com", passwordEncoder.encode("123"),
                new HashSet<>(Collections.singletonList(roleUser)), new HashSet<>()));

    }


}
