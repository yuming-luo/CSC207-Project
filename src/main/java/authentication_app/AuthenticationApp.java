package authentication_app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInteractor;
import view.SignupView;

public class AuthenticationApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create shared dependencies
            InMemoryUserDataAccessObject userDAO =
                    new InMemoryUserDataAccessObject();

            UserFactory userFactory = new UserFactory();

            // Initialize signup components
            SignupViewModel viewModel = new SignupViewModel();

            SignupPresenter presenter =
                    new SignupPresenter(viewModel);

            SignupInteractor interactor =
                    new SignupInteractor(userDAO, presenter, userFactory);

            SignupController controller =
                    new SignupController(interactor);

            SignupView signupView =
                    new SignupView(viewModel, controller);

            // Display the signup window
            JFrame frame = new JFrame("Study Nook - Signup");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(signupView);
            frame.setSize(450, 250);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}