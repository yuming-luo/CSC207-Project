package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;

public class LoggedInView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "logged in";

    private final LoginViewModel loginViewModel;
    private final JLabel welcomeLabel = new JLabel("", SwingConstants.CENTER);

    public LoggedInView(LoginViewModel loginViewModel,
                        LogoutController logoutController) {

        this.loginViewModel = loginViewModel;

        setLayout(new BorderLayout());

        // Display the current username
        add(welcomeLabel, BorderLayout.CENTER);

        // Create logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> logoutController.execute());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loginViewModel.addPropertyChangeListener(this);

        updateView();
    }

    private void updateView() {
        LoginState state = loginViewModel.getState();

        if (state.isLoggedIn()) {
            welcomeLabel.setText("Welcome, " + state.getUsername() + "!");
        }
        else {
            welcomeLabel.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            updateView();
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}