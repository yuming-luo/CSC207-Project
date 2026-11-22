package use_case.signup;

import entity.User;
import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary {

    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(
            SignupUserDataAccessInterface userDataAccessObject,
            SignupOutputBoundary signupPresenter,
            UserFactory userFactory) {

        this.userDataAccessObject = userDataAccessObject;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData inputData) {

        // Validate required signup fields
        if (inputData.getUsername() == null
                || inputData.getUsername().trim().isEmpty()
                || inputData.getPassword() == null
                || inputData.getPassword().isEmpty()
                || inputData.getRepeatPassword() == null
                || inputData.getRepeatPassword().isEmpty()) {

            signupPresenter.prepareFailView(
                    "Username and password cannot be empty."
            );
            return;
        }

        // Check whether the username already exists
        if (userDataAccessObject.existsByName(inputData.getUsername())) {
            signupPresenter.prepareFailView("Username already exists.");
        }

        // Check whether the passwords match
        else if (!inputData.getPassword().equals(
                inputData.getRepeatPassword())) {
            signupPresenter.prepareFailView("Passwords do not match.");
        }

        // Create and save the new user
        else {
            User user = userFactory.create(
                    inputData.getUsername(),
                    inputData.getPassword()
            );

            userDataAccessObject.save(user);

            SignupOutputData outputData =
                    new SignupOutputData(user.getUsername());

            signupPresenter.prepareSuccessView(outputData);
        }
    }
}