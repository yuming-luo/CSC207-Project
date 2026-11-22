package use_case.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;

public class LoginInteractorTest {

    @Test
    void testSuccessfulLogin() {

        // Create test user
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();
        userDAO.save(userFactory.create("testuser", "test123"));

        // Capture login result
        final boolean[] success = {false};

        LoginOutputBoundary presenter = new LoginOutputBoundary() {

            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                success[0] = true;
                assertEquals("testuser", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Login should succeed: " + errorMessage);
            }
        };

        // Execute login
        LoginInteractor interactor =
                new LoginInteractor(userDAO, presenter);

        LoginInputData inputData =
                new LoginInputData("testuser", "test123");

        interactor.execute(inputData);

        // Verify successful login
        assertTrue(success[0]);
    }

    @Test
    void testIncorrectPassword() {

        // Create an existing test user
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();
        userDAO.save(userFactory.create("testuser", "test123"));

        // Capture login failure
        final String[] error = {null};

        LoginOutputBoundary presenter = new LoginOutputBoundary() {

            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                fail("Login should fail with an incorrect password.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                error[0] = errorMessage;
            }
        };

        // Execute login with an incorrect password
        LoginInteractor interactor =
                new LoginInteractor(userDAO, presenter);

        LoginInputData inputData =
                new LoginInputData("testuser", "wrong123");

        interactor.execute(inputData);

        // Verify login was rejected
        assertEquals("Invalid username or password.", error[0]);
    }

    @Test
    void testNonexistentUsername() {

        // Create an empty user DAO
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        // Capture login failure
        final String[] error = {null};

        LoginOutputBoundary presenter = new LoginOutputBoundary() {

            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                fail("Login should fail when the username does not exist.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                error[0] = errorMessage;
            }
        };

        // Execute login with a nonexistent username
        LoginInteractor interactor =
                new LoginInteractor(userDAO, presenter);

        LoginInputData inputData =
                new LoginInputData("unknownuser", "test123");

        interactor.execute(inputData);

        // Verify login was rejected
        assertEquals("Invalid username or password.", error[0]);
        assertFalse(userDAO.existsByName("unknownuser"));
    }
}