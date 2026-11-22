package use_case.signup;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {

    @Test
    void testSuccessfulSignup() {

        // Create test dependencies
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();

        // Capture signup results
        final boolean[] success = {false};

        SignupOutputBoundary presenter = new SignupOutputBoundary() {

            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                success[0] = true;
                assertEquals("testuser", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                throw new AssertionError(errorMessage);
            }
        };

        // Execute signup
        SignupInteractor interactor =
                new SignupInteractor(userDAO, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("testuser", "test123", "test123");

        interactor.execute(inputData);

        // Verify user was saved
        assertTrue(success[0]);
        assertTrue(userDAO.existsByName("testuser"));
    }

    @Test
    void testDuplicateUsername() {

        // Create test dependencies
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();

        userDAO.save(userFactory.create("testuser", "test123"));

        // Capture signup results
        final String[] error = {null};

        SignupOutputBoundary presenter = new SignupOutputBoundary() {

            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Signup should fail for an existing username.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                error[0] = errorMessage;
            }
        };

        // Execute signup with an existing username
        SignupInteractor interactor =
                new SignupInteractor(userDAO, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("testuser", "newpassword", "newpassword");

        interactor.execute(inputData);

        // Verify duplicate username is rejected
        assertEquals("Username already exists.", error[0]);
        assertEquals("test123", userDAO.get("testuser").getPassword());
    }

    @Test
    void testPasswordsDoNotMatch() {

        // Create test dependencies
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();

        // Capture signup failure
        final String[] error = {null};

        SignupOutputBoundary presenter = new SignupOutputBoundary() {

            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Signup should fail when passwords do not match.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                error[0] = errorMessage;
            }
        };

        // Execute signup with different passwords
        SignupInteractor interactor =
                new SignupInteractor(userDAO, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("testuser", "password123", "password456");

        interactor.execute(inputData);

        // Verify the error and ensure no user was saved
        assertEquals("Passwords do not match.", error[0]);
        assertFalse(userDAO.existsByName("testuser"));
    }

    @Test
    void testEmptySignupInput() {

        // Create test dependencies
        InMemoryUserDataAccessObject userDAO =
                new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserFactory();

        // Capture signup failure
        final String[] error = {null};

        SignupOutputBoundary presenter = new SignupOutputBoundary() {

            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Signup should fail with empty input.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                error[0] = errorMessage;
            }
        };

        // Execute signup with an empty username
        SignupInteractor interactor =
                new SignupInteractor(userDAO, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("", "test123", "test123");

        interactor.execute(inputData);

        // Verify signup was rejected
        assertEquals(
                "Username and password cannot be empty.",
                error[0]
        );

        assertFalse(userDAO.existsByName(""));
    }
}