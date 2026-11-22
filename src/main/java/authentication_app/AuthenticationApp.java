package authentication_app;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;

import use_case.login.LoginInteractor;
import use_case.signup.SignupInteractor;
import use_case.logout.LogoutInteractor;

import view.LoginView;
import view.SignupView;
import view.LoggedInView;

public class AuthenticationApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create shared user data access
            InMemoryUserDataAccessObject userDAO =
                    new InMemoryUserDataAccessObject();
            UserFactory userFactory = new UserFactory();

            // Initialize signup components
            SignupViewModel signupViewModel = new SignupViewModel();
            SignupPresenter signupPresenter =
                    new SignupPresenter(signupViewModel);
            SignupInteractor signupInteractor =
                    new SignupInteractor(userDAO, signupPresenter, userFactory);
            SignupController signupController =
                    new SignupController(signupInteractor);
            SignupView signupView =
                    new SignupView(signupViewModel, signupController);

            // Initialize login components
            LoginViewModel loginViewModel = new LoginViewModel();
            LoginPresenter loginPresenter =
                    new LoginPresenter(loginViewModel);
            LoginInteractor loginInteractor =
                    new LoginInteractor(userDAO, loginPresenter);
            LoginController loginController =
                    new LoginController(loginInteractor);
            LoginView loginView =
                    new LoginView(loginViewModel, loginController);

            // Initialize logout components
            LogoutPresenter logoutPresenter =
                    new LogoutPresenter(loginViewModel);

            LogoutInteractor logoutInteractor =
                    new LogoutInteractor(logoutPresenter);

            LogoutController logoutController =
                    new LogoutController(logoutInteractor);

            LoggedInView loggedInView =
                    new LoggedInView(loginViewModel, logoutController);

            // Create navigation between signup and login
            CardLayout cardLayout = new CardLayout();
            JPanel views = new JPanel(cardLayout);

            views.add(signupView, SignupViewModel.VIEW_NAME);
            views.add(loginView, LoginViewModel.VIEW_NAME);
            views.add(loggedInView, LoggedInView.VIEW_NAME);

            // Navigate to login after successful signup
            signupViewModel.addPropertyChangeListener(event -> {
                if ("state".equals(event.getPropertyName())) {
                    SignupState state = signupViewModel.getState();

                    if (state.getError() == null || state.getError().isEmpty()) {
                        cardLayout.show(views, LoginViewModel.VIEW_NAME);
                    }
                }
            });

            loginViewModel.addPropertyChangeListener(event -> {
                if ("state".equals(event.getPropertyName())) {
                    if (loginViewModel.getState().isLoggedIn()) {
                        cardLayout.show(views, LoggedInView.VIEW_NAME);
                    }
                    else if (loginViewModel.getState().getError().isEmpty()) {
                        cardLayout.show(views, LoginViewModel.VIEW_NAME);
                    }
                }
            });

            JButton showSignupButton = new JButton("Sign Up");
            JButton showLoginButton = new JButton("Log In");

            showSignupButton.addActionListener(e ->
                    cardLayout.show(views, SignupViewModel.VIEW_NAME));

            showLoginButton.addActionListener(e ->
                    cardLayout.show(views, LoginViewModel.VIEW_NAME));

            JPanel navigationPanel = new JPanel();
            navigationPanel.add(showSignupButton);
            navigationPanel.add(showLoginButton);

            // Update navigation based on login state
            loginViewModel.addPropertyChangeListener(event -> {
                if ("state".equals(event.getPropertyName())) {
                    LoginState state = loginViewModel.getState();

                    if (state.isLoggedIn()) {
                        navigationPanel.setVisible(false);
                        cardLayout.show(views, LoggedInView.VIEW_NAME);
                    }
                    else if (state.getError().isEmpty()) {
                        navigationPanel.setVisible(true);
                        cardLayout.show(views, LoginViewModel.VIEW_NAME);
                    }
                }
            });

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(navigationPanel, BorderLayout.NORTH);
            mainPanel.add(views, BorderLayout.CENTER);

            // Display authentication application
            JFrame frame = new JFrame("Study Nook - Authentication");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(mainPanel);
            frame.setSize(450, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}