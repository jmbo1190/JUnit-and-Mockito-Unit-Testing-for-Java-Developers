package com.javaeasily.demos.mockito.myapp.service;

import com.javaeasily.demos.mockito.myapp.data.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AuthenticationServiceTest {

    private AuthenticationService service; // SUT

    private UserRepository repository;  // mock

    @Before
    public void setUp() {
        this.repository = Mockito.mock(UserRepository.class);
        this.service = new AuthenticationService(repository);
    }

    @Test
    public void testAuthenticate() {
        // arrange - we want to test how AuthenticationService class will behave
        //           in case it receives an exception when accessing the repository
        //           (exceptions have not been handled so we can except the application to crash)
        Mockito.when(this.repository.findByUsername(Mockito.anyString())).thenThrow(new IllegalArgumentException());

        // act
        this.service.authenticate("harry", "harry12345");

        // assert - we won't even reach that section
    }

}