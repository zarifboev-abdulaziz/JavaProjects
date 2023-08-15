package uz.pdp.websocketdemo.common;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.websocketdemo.model.Role;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.RoleRepository;
import uz.pdp.websocketdemo.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("never"))return;

        Role user_role = roleRepository.save(new Role("USER_ROLE"));
        HashSet<Role> roles = new HashSet<>(Arrays.asList(user_role));

        userRepository.save(new User(null, "Asil", "asil", passwordEncoder.encode("123"), roles));
        userRepository.save(new User(null, "Abdulaziz", "abdulaziz", passwordEncoder.encode("123"), roles));
        userRepository.save(new User(null, "Aziz", "aziz", passwordEncoder.encode("123"), roles));
        userRepository.save(new User(null, "Nodirbek", "nodirbek", passwordEncoder.encode("123"), roles));

    }



}
