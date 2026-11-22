package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    public void execute(String username, String password,
                        String repeatPassword) {

        // Create input data from user input
        SignupInputData inputData =
                new SignupInputData(username, password, repeatPassword);

        // Execute the signup use case
        signupInteractor.execute(inputData);
    }
}