package interface_adapter.logout;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;

    public LogoutPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView() {

        // Clear the login state
        LoginState state = loginViewModel.getState();

        state.setUsername("");
        state.setPassword("");
        state.setError("");
        state.setLoggedIn(false);

        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();
    }
}