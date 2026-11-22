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
import interface_adapter.login.LoginViewModel;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;

import use_case.login.LoginInteractor;
import use_case.signup.SignupInteractor;

import view.LoginView;
import view.SignupView;

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

            // Create navigation between signup and login
            CardLayout cardLayout = new CardLayout();
            JPanel views = new JPanel(cardLayout);

            views.add(signupView, SignupViewModel.VIEW_NAME);
            views.add(loginView, LoginViewModel.VIEW_NAME);

            JButton showSignupButton = new JButton("Sign Up");
            JButton showLoginButton = new JButton("Log In");

            showSignupButton.addActionListener(e ->
                    cardLayout.show(views, SignupViewModel.VIEW_NAME));

            showLoginButton.addActionListener(e ->
                    cardLayout.show(views, LoginViewModel.VIEW_NAME));

            JPanel navigationPanel = new JPanel();
            navigationPanel.add(showSignupButton);
            navigationPanel.add(showLoginButton);

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