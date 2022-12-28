# Using Mockito to test the LoginController

In this video we're going to use Mockito to test the LoginController.
Okay, so we're going to test the LoginController and its service method.

I go to LoginController class, shift+command+T, Create a New Test Class.

It will have:
 * a service test method: `testService`, with annotation @Test.
 * a `setUp` method, with annotation @Before.

## Arrange

In order to test this LoginController weneed to:
 1. create an instance of it.
 2. make sure that, that instance also has an AuthenticationService field as well.
    Not the real AuthenticationService; instead we're going to set the mock AuthenticationService. 

So therefore going back to our test class, we want to have two fields:
 - the LoginController itself, which is the system under test (SUT)
 - the AuthenticationService, which is going to be a mock.
 
So we're going to set these up in the setup method:

    this.service = Mockito.mock(AuthenticationService.class); 
    this.controller = new LoginController(this.service);  // need to add the constructor
    
    
Let's go into LoginController and let's add a constructor which takes the AuthenticationService.

    public LoginController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }


We must be able to access the mock to set it up to act the way we want it to act inside each individual test method, so we need it in a separate member reference, on the controller.

### Setting Up the mock
So now arrange, this is where we have an opportunityto set up our mock, to set it up as we want it to act.

We will import static methods from Mockito.*:
 - the mock method, 
 - the when method and 
 - the then method.

This isn't a data test, we're not testing a function as we've seen before, what we're testing nowis actual behavior, which is a difference, so whatever strings are passed as useraname and as password, we want the method to return true.
So we say:

    when(service.authenticate(anyString(), anyString()
        ).thenReturn(true);

So we're setting up the mock to act as if the authenticate method always returns true, a happy path scenario.

Normally the authenticate method of the AuthenticationService class, delegates to the user repository, finds the user by the username, gets a user object, then looks whether the password on the object is equal to the supplied password. 
That's what it usually does.
However, with the mockis we're basically commenting all that out and replacing with the custom implementation, which is to effectively always return true.

## Act
What happens when the user is going to be logged in, from a production code point of view? 
Well, let's look back at the LoginController and look at the logic there.
If this method here, if AuthenticationService.authenticate passing in the username and password.
If it does equal true alright, then we're going to return the path to the home page.And if it's not true, we return the path to the login page, but we're working with the fact that it is true now because that's how we've setup our mock for this specific test. So when it is true, we expect that the user will be effectively sent to the home page.

## Assert
Okay, so how do we communicate like the test?
Jump back to the test, shift command T again log and control the test.
We basically do the action. So this is where we're going to call the controller. We can prefix it with 'this.' also, but we don't necessarily have to. In fact, I prefer not to. So we'll also take it out from here as well. 
The action now is controller.service and we can pass in any old thing now it doesn't really matter, tom password123. But the key point here, of course is that we get back the view name or viewPath. Let's call it viewPath. So in this case when we get the viewPath back we expect now that it's equal to /home so we can do the Assert.
assertNotNull(viewPath): We don't want to get back a null viewPath. That will be wrong. 
Next, Assert.assertEquals(expect, actual):
So we expected /home and the actual is viewPath. 
So now if we run this we can see that that test has passed and we probably want to give it a more descriptive test name as well. 
For example, testService_validUsernameAndPassword_logsInUser, for example. 
It means we've basically got a test method now which is named descriptively to describe what the test is actually testing.