package uz.pdp.softwaretestingdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.pdp.softwaretestingdemo.entity.User;
import uz.pdp.softwaretestingdemo.exception.BadRequestException;
import uz.pdp.softwaretestingdemo.exception.UserNotFoundException;
import uz.pdp.softwaretestingdemo.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(userRepository);
    }


    @Test
    void canGetAllUsers() {
        // when
        userServiceUnderTest.getAllUsers();
        // then
        verify(userRepository).findAll();
    }

    @Test
    void canAddUser() {
        // given
        User user = new User(null, "demoName", "demoUsername");

        // when
        userServiceUnderTest.saveUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository)
                .save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenUsernameExists() {
        // given
        User user = new User(null, "demo name", "demo username");

        given(userRepository.existsByUsername(anyString())).willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> userServiceUnderTest.saveUser(user))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(user.getUsername() + " is already taken.");

        verify(userRepository, never()).save(any());
    }

    @Test
    void canEditUser() {
        // given
//        userServiceUnderTest.saveUser(new User(10, "qwerty", "qwerty"));
//        userRepository.save();
        Integer id = 10;
        User user = new User(null, "demoName", "demoUsername");
        given(userRepository.save(new User(10, "qwerty", "qwerty")));

        // when
        userServiceUnderTest.editUser(id, user);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository)
                .save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canDeleteUser() {
        // given
        Integer id = 10;
        given(userRepository.existsById(anyInt())).willReturn(true);

        // when
        ResponseEntity<?> responseEntity = userServiceUnderTest.deleteUser(id);
        HttpStatus actualStatusCode = responseEntity.getStatusCode();
        HttpStatus expectedStatusCode = HttpStatus.NO_CONTENT;

        assertEquals(expectedStatusCode, actualStatusCode);
        assertEquals("User successfully deleted", responseEntity.getBody());

        // then
        verify(userRepository).deleteById(id);
    }

    @Test
    void willThrowExceptionWhenDeletingStudentNotFound() {
        // given
        Integer id = 10;
        given(userRepository.existsById(id))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() -> userServiceUnderTest.deleteUser(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User with id: " + id + " not found!");

        verify(userRepository, never()).deleteById(any());
    }

    @Test
    void willThrowExceptionWhenEditingStudent() {
        // given
        Integer id = 10;
        String username = "demo username";
        given(userRepository.existsByUsernameAndIdNot(username, id))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> userServiceUnderTest.editUser(id, new User(id, "demo", username)))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(username + " is already taken.");

        verify(userRepository, never()).deleteById(any());
    }



}