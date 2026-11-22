package interface_adapter.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;

    public LoginPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {

        // Update login state after successful authentication
        LoginState state = loginViewModel.getState();

        state.setUsername(outputData.getUsername());
        state.setPassword("");
        state.setError("");

        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

        // Update login state with an error message
        LoginState state = loginViewModel.getState();

        state.setError(errorMessage);

        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();
    }
}