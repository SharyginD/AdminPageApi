package my.project.service;

import my.project.domain.dto.User;
import my.project.domain.entity.UserEntity;
import my.project.exception.customException.ResourceNotFoundException;
import my.project.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserEntity userEntity1;
    private UserEntity userEntity2;
    private UserEntity userEntity3;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void init() {
        userEntity1 = new UserEntity(1, "Saske", "Saske", "saske@mail.ru");
        userEntity2 = new UserEntity(2, "Itachi", "Itachi", "itachi@mail.ru");
        userEntity3 = new UserEntity(3, "Naruto", "Naruto", "naruto@mail.ru");

        user1 = new User(1, "Saske", "Saske", "saske@mail.ru");
        user2 = new User(2, "Itachi", "Itachi", "itachi@mail.ru");
        user3 = new User(3, "Naruto", "Naruto", "naruto@mail.ru");
    }

    @Test
    public void getAllUsersTest() {

        List<UserEntity> userEntityList = new ArrayList<>(Arrays.asList(userEntity1, userEntity2, userEntity3));
        List<User> userEntity = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Mockito.when(userRepository.findAll()).thenReturn(userEntityList);

        Assert.assertEquals(userEntity, userService.getAll());
    }

    @Test
    public void saveUserTest() {

        Mockito.when(userRepository.save(Mockito.eq(userEntity1))).thenReturn(userEntity1);
        Assert.assertEquals(user1, userService.save(user1));
    }

    @Test
    public void deleteUserTest() {

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(userEntity1));
        Assert.assertEquals(user1, userService.delete(1));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenDeleteUser_ThenThrowResourceNotFoundException() {

        Mockito.when(userRepository.findById(1)).thenThrow(new ResourceNotFoundException("User with id = " + 1 + " not found"));
        userService.delete(1);
    }
}

