package com.javaeasily.demos.mockito.myapp.service;

import com.javaeasily.demos.mockito.myapp.User;
import com.javaeasily.demos.mockito.myapp.User.UserType;
import com.javaeasily.demos.mockito.myapp.data.UserRepository;
//import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserLookupServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserLookupService userLookupService;

    @Test
    public void getRegularUsers() {
        // arrange
        List<User> userList = new LinkedList<>();
        userList.add(User.createRegularUser("anne", "abc123"));
        userList.add(User.createRegularUser("donald", "dbc321"));
        userList.add(User.createAdminUser("beebop", "pwd098"));

        when(userRepository.findAll()).thenReturn(userList);

        // act
        Set<User> actualUsers = userLookupService.getRegularUsers();

        // assert
        // general collection
        assertNotNull(actualUsers);
        assertEquals(2, actualUsers.size());

        // loop through users
        for (User actualUser : actualUsers) {
            assertNotNull(actualUser);
            assertEquals(User.UserType.REGULAR_USER, actualUser.getUserType());
        }

        // User 1
        Object[] actualUsersArray = actualUsers.toArray();

        User ActualUser1 = (User) actualUsersArray[0];
        assertNotNull(ActualUser1);
        assertEquals(UserType.REGULAR_USER, ActualUser1.getUserType());
        assertNotNull(ActualUser1.getUsername());
        assertNotNull(ActualUser1.getPassword());

        // User 2
        User ActualUser2 = (User) actualUsersArray[1];
        assertNotNull(ActualUser2);
        assertEquals(UserType.REGULAR_USER, ActualUser2.getUserType());
        assertNotNull(ActualUser2.getUsername());
        assertNotNull(ActualUser2.getPassword());
        
    }

    @Test
    public void getAdminUsers() {
        // arrange

        // act

        // assert
    }
}