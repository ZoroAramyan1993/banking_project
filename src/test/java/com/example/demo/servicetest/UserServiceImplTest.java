package com.example.demo.servicetest;

import com.example.demo.entity.User;
import com.example.demo.enums.RoleName;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.Assume;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.BDDMockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Test
    public void when_save_user_it_should_has_email() {
        User user = new User();
        user.setEmail("aaaa@mail.ru");
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setRoleName(RoleName.USER);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        User user1 = userServiceImpl.save(user);
        assertEquals("aaaa@mail.ru", user1.getEmail());
    }

    @Test
    public void when_save_user_without_email_it_throw_exception() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            User user = new User();
            user.setPassword("asaaaaaa");
            user.setName("aaaa");
            user.setRoleName(RoleName.USER);
            userServiceImpl.save(user);
        });
    }

    @Test
    public void when_save_user_without_name_it_throw_exception() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            User user = new User();
            user.setPassword("asaaaaaa");
            user.setEmail("aaaaaa@.mail.ru");
            user.setRoleName(RoleName.USER);
            userServiceImpl.save(user);
        });
    }

    @Test
    public void when_save_user_without_role_it_throw_exception() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            User user = new User();
            user.setPassword("asaaaaaa");
            user.setName("aaaa");
            user.setEmail("aaaaaa@.mail.ru");
            userServiceImpl.save(user);
        });
    }


    @Test
    public void when_save_user_it_should_be_done_successfully() {
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        User user1 = userServiceImpl.save(user);
        Assume.assumeNotNull(user1);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void when_save_user_with_existing_email_it_should_throw_error() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            User user = new User();
            user.setPassword("asaaaaaa");
            user.setName("aaaa");
            user.setEmail("aaaaaa@.mail.ru");
            user.setRoleName(RoleName.USER);

            given(userRepository.existsByEmail(user.getEmail())).willReturn(true);
            userServiceImpl.save(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void update_user_test() {
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(10);
        given(userRepository.save(user)).willReturn(user);
        User user1 = userServiceImpl.update(user);
        Assertions.assertEquals(user1, user);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void find_all_test() {
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(10);
        User user1 = new User();
        user1.setPassword("asaaaaaa");
        user1.setName("aaaa");
        user1.setEmail("aa1458aaaa@.mail.ru");
        user1.setRoleName(RoleName.USER);
        user1.setId(11);
        users.add(user);
        users.add(user1);
        given(userRepository.findAll()).willReturn(users);
        List<User> users1 = userServiceImpl.findAll();
        assertEquals(users1, users);
    }

    @Test
    public void find_by_id_test() {
        final Integer id = 1;
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(id);
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        final Optional<User> expected = userServiceImpl.findById(id);
        assertEquals(expected, Optional.of(user));
    }

    @Test
    public void find_by_email_test() {
        final Integer id = 1;
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(id);
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        final User expected = userServiceImpl.findByEmail(user.getEmail());
        assertEquals(expected, user);
    }

    @Test
    public void delete_test() {
        final Integer id = 1;
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(id);
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        userServiceImpl.delete(id);
        userServiceImpl.delete(id);
        verify(userRepository, times(2)).deleteById(id);
    }

    @Test
    public void exist_by_email_test() {
        final Integer id = 1;
        User user = new User();
        user.setPassword("asaaaaaa");
        user.setName("aaaa");
        user.setEmail("aaaaaa@.mail.ru");
        user.setRoleName(RoleName.USER);
        user.setId(id);
        given(userRepository.existsByEmail(user.getEmail())).willReturn(true);
        Boolean expected = userServiceImpl.existsByEmail(user.getEmail());
        assertEquals(expected, true);
    }


}
