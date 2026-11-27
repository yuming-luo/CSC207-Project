package app;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import javafx.scene.Node;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

public class AuthBuilder {
    private final LoginUserDataAccessInterface userDao;

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final LoginViewModel loginViewModel = new LoginViewModel();
    private final SignupViewModel signupViewModel = new SignupViewModel();
    private final UserFactory userFactory = new UserFactory();

    public AuthBuilder(LoginUserDataAccessInterface userDao) {
        this.userDao = userDao;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public Node build() {
        ContentPane container = new ContentPane();
        new ViewManager(container, viewManagerModel);

        // Login Initializer
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userDao, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        // Signup Initializer
        SignupOutputBoundary signupPresenter = new SignupPresenter(signupViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor((SignupUserDataAccessInterface) userDao, signupPresenter, userFactory);
        SignupController signupController = new SignupController(signupInteractor);

        LoginView loginView = new LoginView(loginViewModel);
        loginView.setLoginController(loginController);
        loginView.addNavigationListener(e -> {
            if (LoginView.SIGN_UP_CLICKED.equals(e.getPropertyName())) {
                showView(SignupViewModel.VIEW_NAME);
            }
        });

        SignupView signupView = new SignupView(signupViewModel);
        signupView.setSignupController(signupController);
        signupView.addNavigationListener(e -> {
            if (SignupView.LOGIN_CLICKED.equals(e.getPropertyName())) {
                showView(LoginViewModel.VIEW_NAME);
            }
        });

        container.addView(LoginViewModel.VIEW_NAME, loginView);
        container.addView(SignupViewModel.VIEW_NAME, signupView);
        container.showView(LoginViewModel.VIEW_NAME);
        return container;
    }

    private void showView(String viewName) {
        viewManagerModel.setState(viewName);
        viewManagerModel.notifyViewChanged();
    }
}