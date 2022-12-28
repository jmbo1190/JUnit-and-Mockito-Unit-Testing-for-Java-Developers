package com.javaeasily.demos.mockito.myapp.web;

import com.javaeasily.demos.mockito.myapp.service.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController controller;   // SUT

    private AuthenticationService service;   // mock

    @Before
    public void setUp() throws Exception {
        this.service = Mockito.mock(AuthenticationService.class);
        this.controller = new LoginController(this.service);
    }

    @Test
    public void testService_validUsernameAndPassword_logsInUser() {
        // arrange: setup the mock to act as if the authenticate method
        // is always successful, i.e. it always returns true
        when(this.service.authenticate(anyString(), anyString())
        ).thenReturn(true);

        // act - note: the prexix 'this.' is optional
        String viewPath = this.controller.service("tom", "password123");

        // assert
        Assert.assertNotNull(viewPath);
        Assert.assertEquals("/home", viewPath);
    }

    @Test
    public void testService_validUsernameAndPassword2_logsInUser() {
        // arrange: setup the mock to act as if the authenticate method
        // is always successful, i.e. it always returns true
        Mockito.when(service.authenticate(anyString(), anyString())
        ).thenReturn(true);

        // act
        String viewPath = controller.service("XXX", "don'tcare");

        // assert
        Assert.assertNotNull(viewPath);
        Assert.assertEquals("/home", viewPath);  // correct result
        // Assert.assertEquals("/home2", viewPath); // see what happens when test fails
    }
}