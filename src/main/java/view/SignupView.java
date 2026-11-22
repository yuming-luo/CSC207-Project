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

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

public class SignupView extends JPanel implements PropertyChangeListener {

    private final SignupViewModel signupViewModel;
    private final SignupController signupController;

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JPasswordField repeatPasswordField = new JPasswordField(15);

    public SignupView(SignupViewModel signupViewModel,
                      SignupController signupController) {

        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        setLayout(new GridLayout(4, 2, 10, 10));

        // Add username input
        add(new JLabel("Username:"));
        add(usernameField);

        // Add password input
        add(new JLabel("Password:"));
        add(passwordField);

        // Add repeated password input
        add(new JLabel("Repeat Password:"));
        add(repeatPasswordField);

        // Create signup button
        JButton signupButton = new JButton("Sign Up");

        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String repeatPassword =
                    new String(repeatPasswordField.getPassword());

            signupController.execute(username, password, repeatPassword);
        });

        add(new JLabel(""));
        add(signupButton);

        signupViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            SignupState state = signupViewModel.getState();

            if (state.getError() != null && !state.getError().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        state.getError(),
                        "Signup Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {
                passwordField.setText("");
                repeatPasswordField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Account created successfully!",
                        "Signup Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    public String getViewName() {
        return SignupViewModel.VIEW_NAME;
    }
}