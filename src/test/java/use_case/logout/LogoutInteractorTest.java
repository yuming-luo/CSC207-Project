package use_case.logout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LogoutInteractorTest {

    @Test
    void testSuccessfulLogout() {

        // Capture the logout result
        final boolean[] success = {false};

        LogoutOutputBoundary presenter = new LogoutOutputBoundary() {

            @Override
            public void prepareSuccessView() {
                success[0] = true;
            }
        };

        // Execute the logout use case
        LogoutInteractor interactor =
                new LogoutInteractor(presenter);

        interactor.execute();

        // Verify the presenter was notified
        assertTrue(success[0]);
    }
}