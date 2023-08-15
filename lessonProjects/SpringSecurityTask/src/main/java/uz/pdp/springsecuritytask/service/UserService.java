package uz.pdp.springsecuritytask.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.springsecuritytask.model.User;
import uz.pdp.springsecuritytask.payload.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);


}
