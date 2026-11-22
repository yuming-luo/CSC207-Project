package view;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

public class LoginView extends JPanel implements PropertyChangeListener {

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);

    public LoginView(LoginViewModel loginViewModel,
                     LoginController loginController) {

        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        setLayout(new GridLayout(3, 2, 10, 10));

        // Add username and password fields
        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        // Create login button
        JButton loginButton = new JButton("Log In");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            loginController.execute(username, password);
        });

        add(new JLabel(""));
        add(loginButton);

        loginViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            LoginState state = loginViewModel.getState();

            // Clear the password field
            passwordField.setText("");

            if (state.getError() != null && !state.getError().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        state.getError(),
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else if (state.isLoggedIn()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Welcome, " + state.getUsername() + "!",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    public String getViewName() {
        return LoginViewModel.VIEW_NAME;
    }
}