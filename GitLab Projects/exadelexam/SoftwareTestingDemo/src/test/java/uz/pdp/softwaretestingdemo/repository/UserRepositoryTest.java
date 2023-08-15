package uz.pdp.softwaretestingdemo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import uz.pdp.softwaretestingdemo.entity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void itShouldCheckWhetherUsernameExists() {
        //given
        String givenUsername = "user";
        User user = new User(null, "qwerty",givenUsername);
        userRepository.save(user);

        //when
        boolean actualValue = userRepository.existsByUsername(givenUsername);

        //then
        assertThat(actualValue).isTrue();
    }



    @Test
    void itShouldCheckWhetherUsernameExistsAndIdNot() {
        //given
        User user = new User(null, "Name Surname", "user");
        User anotherUser = new User(null, "1Name 1Surname", "user");
        User savedUser = userRepository.save(user);

        //when
        boolean actualValue = userRepository.existsByUsernameAndIdNot(savedUser.getUsername(), savedUser.getId());
        //then
        assertThat(actualValue).isFalse();

        //also
        User user1 = userRepository.save(anotherUser);
        boolean actualValue2 = userRepository.existsByUsernameAndIdNot(user1.getUsername(), user1.getId());
        assertThat(actualValue2).isTrue();
    }

}