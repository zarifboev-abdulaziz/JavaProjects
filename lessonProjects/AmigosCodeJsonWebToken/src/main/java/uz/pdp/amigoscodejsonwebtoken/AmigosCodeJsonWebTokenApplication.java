package uz.pdp.amigoscodejsonwebtoken;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.amigoscodejsonwebtoken.domain.Role;
import uz.pdp.amigoscodejsonwebtoken.domain.User;
import uz.pdp.amigoscodejsonwebtoken.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class AmigosCodeJsonWebTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmigosCodeJsonWebTokenApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John Travolta", "john", "123", new ArrayList<>()));
            userService.saveUser(new User(null, "Will Smith", "will", "123", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "123", new ArrayList<>()));
            userService.saveUser(new User(null, "Arnold Swach", "arnold", "123", new ArrayList<>()));


            userService.addRoleToUser("john", "ROLE_USER");

            userService.addRoleToUser("will", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_MANAGER");

            userService.addRoleToUser("jim", "ROLE_USER");
            userService.addRoleToUser("jim", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");

            userService.addRoleToUser("arnold", "ROLE_USER");
            userService.addRoleToUser("arnold", "ROLE_MANAGER");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

        };
    }

}
