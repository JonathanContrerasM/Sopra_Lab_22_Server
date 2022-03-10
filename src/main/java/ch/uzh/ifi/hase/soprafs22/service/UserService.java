package ch.uzh.ifi.hase.soprafs22.service;

import ch.uzh.ifi.hase.soprafs22.constant.CurrentDate;
import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;
import ch.uzh.ifi.hase.soprafs22.entity.User;
import ch.uzh.ifi.hase.soprafs22.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs22.rest.dto.UserGetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * User Service
 * This class is the "worker" and responsible for all functionality related to
 * the user
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back
 * to the caller.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User newUser) {
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(UserStatus.ONLINE);

        checkIfUsernameExists(newUser);
        //If a new user registers the current date is fetched and stored
        newUser.setRegistrationDate(CurrentDate.getDate());

        // saves the given entity but data is only persisted in the database once
        // flush() is called

        newUser = userRepository.save(newUser);
        userRepository.flush();

        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User loginUser(User loginUser) {
        if (loginUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("username not found"));
        }
        User tempUser = userRepository.findByUsername(loginUser.getUsername());

        //Check if the input password equals the one matching with the username
        if (tempUser.getPassword().equals(loginUser.getPassword())) {
            loginUser.setStatus(UserStatus.ONLINE);
            tempUser.setStatus(UserStatus.ONLINE);

            log.debug("Login worked {}", loginUser);
            return tempUser;
        }
        //if bug check for the exception
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("password does not match"));
    }

    /**
     * This is a helper method that will check the uniqueness criteria of the
     * username and the name
     * defined in the User entity. The method will do nothing if the input is unique
     * and throw an error otherwise.
     *
     * @param userToBeCreated
     * @throws org.springframework.web.server.ResponseStatusException
     * @see User
     */
    private void checkIfUserExists(User userToBeCreated) {
        User userByUsername = userRepository.findByUsername(userToBeCreated.getUsername());
        User userByName = userRepository.findByName(userToBeCreated.getName());

        String baseErrorMessage = "The %s provided %s not unique. Therefore, the user could not be created!";
        if (userByUsername != null && userByName != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(baseErrorMessage, "username and the name", "are"));
        }
        else if (userByUsername != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(baseErrorMessage, "username", "is"));
        }
        else if (userByName != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(baseErrorMessage, "name", "is"));
        }
    }

    private void checkIfUsernameExists(User userToBeCreated) {
        User userByUsername = userRepository.findByUsername(userToBeCreated.getUsername());

        String baseErrorMessage = "The %s provided %s not unique. Therefore, the user could not be created!";
        if (userByUsername != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format(baseErrorMessage, "username", "is"));
        }
    }

    public User getUserById(long id) {
        User userById = userRepository.findById(id);
        //check if the user even exists
        if (userById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("The user with this id does not exist!"));
        }
        return userById;
    }


    public void setUserOffline(User userOffline) {
        userOffline.setStatus(UserStatus.OFFLINE);
    }

    public User updateUser(User inputUser, String username, String birthDate) {
        //Add check if Username is already taken
        if (inputUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("The user with this id does not exist!"));
        }


        inputUser.setUsername(username);
        inputUser.setBirthDate(birthDate);

        userRepository.save(inputUser);
        userRepository.flush();

        log.debug("Updated information for User: {}", inputUser);
        return inputUser;

    }
}
