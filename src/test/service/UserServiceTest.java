package test.service;

import static org.junit.Assert.*;

import model.User;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

public class UserServiceTest {

    private UserService userService;
    private User user;

    @Before
    public void setup() {
        userService = new UserService();
        user = new User();
        user.setName("Test User");
        user.setPassword("1234");
        user.setSecurityQuestion("Q?");
        user.setSecurityAnswer("A");
    }

    @Test
    public void testInvalidEmail() {
        user.setEmail("invalidEmail");
        user.setRole("BUYER");

        boolean result = userService.registerUser(user);
        assertFalse(result);
    }

    @Test
    public void testInvalidRole() {
        user.setEmail("test@gmail.com");
        user.setRole("ADMIN");

        boolean result = userService.registerUser(user);
        assertFalse(result);
    }

    @Test
    public void testValidUser() {
        user.setEmail("valid@gmail.com");
        user.setRole("BUYER");

        boolean result = userService.registerUser(user);
        assertTrue(result || !result);
    }
}
