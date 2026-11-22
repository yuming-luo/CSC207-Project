package use_case.login;

import entity.User;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(
            LoginUserDataAccessInterface userDataAccessObject,
            LoginOutputBoundary loginPresenter) {

        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData inputData) {

        // Retrieve the user by username
        User user = userDataAccessObject.get(inputData.getUsername());

        // Validate login credentials
        if (user == null
                || !user.getPassword().equals(inputData.getPassword())) {
            loginPresenter.prepareFailView(
                    "Invalid username or password."
            );
            return;
        }

        // Prepare successful login result
        LoginOutputData outputData =
                new LoginOutputData(user.getUsername());

        loginPresenter.prepareSuccessView(outputData);
    }
}