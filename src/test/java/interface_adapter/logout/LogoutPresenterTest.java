package interface_adapter.logout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

public class LogoutPresenterTest {

    @Test
    void testLogoutClearsLoginState() {

        // Create a logged-in user state
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState state = loginViewModel.getState();

        state.setUsername("testuser");
        state.setPassword("test123");
        state.setLoggedIn(true);

        // Execute logout presentation
        LogoutPresenter presenter =
                new LogoutPresenter(loginViewModel);

        presenter.prepareSuccessView();

        // Verify login state is cleared
        LoginState updatedState = loginViewModel.getState();

        assertFalse(updatedState.isLoggedIn());
        assertEquals("", updatedState.getUsername());
        assertEquals("", updatedState.getPassword());
        assertEquals("", updatedState.getError());
    }
}