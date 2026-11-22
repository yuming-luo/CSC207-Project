package interface_adapter.signup;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;

    public SignupPresenter(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData outputData) {

        // Update the signup state after successful registration
        SignupState state = signupViewModel.getState();

        state.setUsername(outputData.getUsername());
        state.setPassword("");
        state.setRepeatPassword("");
        state.setError("");

        signupViewModel.setState(state);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

        // Update the signup state with an error message
        SignupState state = signupViewModel.getState();

        state.setError(errorMessage);

        signupViewModel.setState(state);
        signupViewModel.firePropertyChanged();
    }
}