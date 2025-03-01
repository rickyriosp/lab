package com.riosr.sq_ch9;

import com.riosr.sq_ch9.controllers.LoginController;
import com.riosr.sq_ch9.service.LoginCountService;
import com.riosr.sq_ch9.service.LoginProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginControllerUnitTests {

    @Mock
    private LoginProcessor loginProcessor;

    @Mock
    private LoginCountService loginCountService;

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginPostLoginSucceedsTest() {
        given(loginProcessor.login()).willReturn(true);

        String result = loginController.loginPost(model,"ricky", "password");

        assertEquals("redirect:/main", result);
        verify(model).addAttribute("message", "You are now logged in.");
    }

    @Test
    public void loginPostLoginFailsTest() {
        given(loginProcessor.login()).willReturn(false);

        String result = loginController.loginPost(model,"ricky", "password");

        assertEquals("login.html", result);
        verify(model).addAttribute("message", "Login failed!");
    }
}
