package ch.uzh.ifi.hase.soprafs22.service;

import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;
import ch.uzh.ifi.hase.soprafs22.entity.User;
import ch.uzh.ifi.hase.soprafs22.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the UserResource REST resource.
 *
 * @see UserService
 */
@WebAppConfiguration
@SpringBootTest
public class UserServiceIntegrationTest {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser_validInputs_success() {
        // given
        assertNull(userRepository.findByUsername("testUsername"));

        User testUser = new User();
        testUser.setName("testName");
        testUser.setUsername("testUsername");
        testUser.setPassword("abcdef123");

        // when
        User createdUser = userService.createUser(testUser);

        // then
        assertEquals(testUser.getId(), createdUser.getId());
        assertEquals(testUser.getName(), createdUser.getName());
        assertEquals(testUser.getUsername(), createdUser.getUsername());
        assertEquals(testUser.getPassword(), createdUser.getPassword());
        assertNotNull(createdUser.getToken());
        assertEquals(UserStatus.ONLINE, createdUser.getLogged_in());
    }

    @Test
    public void createUser_duplicateUsername_throwsException() {
        assertNull(userRepository.findByUsername("testUsername"));

        User testUser = new User();
        testUser.setName("testName");
        testUser.setUsername("testUsername");
        testUser.setPassword("abcdef123");
        User createdUser = userService.createUser(testUser);

        // attempt to create second user with same username
        User testUser2 = new User();

        // change the name but forget about the username
        testUser2.setName("testName2");
        testUser2.setUsername("testUsername");
        testUser2.setPassword("123abcdef");

        // check that an error is thrown
        assertThrows(ResponseStatusException.class, () -> userService.createUser(testUser2));
    }

    // Nr 4
    // fifth is in UserControllerTest
    // user not found id does not exist should return 404 + string
    @Test
    public void getUser_IdNonExistent_throwsException() {
        assertNull(userRepository.findByUsername("testUsername"));

        assertThrows(ResponseStatusException.class, () -> userService.getUserById(1L));
    }

    // Nr 6
    // User should be updated but was not found
    @Test
    public void updateUser_IdNonExistent_throwsException() {
        assertNull(userRepository.findByUsername("testUsername"));

        //userController got the user entity earlier and passes the whole user on if it was not found
        //everything will be null and the userService will throw an error
        User userToBeUpdated = null;

        String newUsername = "newUsername";
        Date newBirthDate = new Date();

        // check that an error is thrown
        assertThrows(ResponseStatusException.class, () -> userService.updateUser(userToBeUpdated, newUsername, newBirthDate));
    }


}
