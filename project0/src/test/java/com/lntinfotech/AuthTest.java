package com.lntinfotech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lntinfotech.models.User;
import com.lntinfotech.services.AuthService;
import com.lntinfotech.services.AuthServiceImpl;
import com.lntinfotech.services.UserService;
import com.lntinfotech.services.UserServiceImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class AuthTest {
    private static AuthService authService;

    @BeforeAll
    public static void setUp() {
        authService = new AuthServiceImpl();
    }

    @AfterAll
    public static void tearDown() {
    }

    @Order(1)
    @Test
    public void login() {
        User expected = new User(1, "ezemog1996", "password", "employee");
        User actualResult = authService.login(new User("ezemog1996", "password"));

        assertEquals(expected, actualResult);
    }

    @Order(2)
    @Test
    public void signUp() {
        boolean expected = true;
        boolean actualResult = authService.register(new User("lkfasd", "fjslk"));
        UserService userService = new UserServiceImpl();
        userService.deleteUser("lkfasd");
        assertEquals(expected, actualResult);
    }
}
