package uz.pdp.softwaretestingdemo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uz.pdp.softwaretestingdemo.controller.UserController;
import uz.pdp.softwaretestingdemo.entity.User;
import uz.pdp.softwaretestingdemo.repository.UserRepository;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class UserIT {
    @Autowired
    UserController userController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;

    @Test
    void canAddNewUser() throws Exception {
        //given
        String username = "user2";
        User user = new User(null, "demo name", username);

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        );

        //then
        resultActions.andExpect(status().isOk());


        boolean existsByUsername = userRepository.existsByUsername(username);
        assertTrue(existsByUsername);

        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()){
            assertThat(byUsername.get())
                    .isEqualToIgnoringGivenFields(user, "id");
        }


    }




}
