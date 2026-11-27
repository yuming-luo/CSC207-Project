package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public class LoginView extends StackPane implements PropertyChangeListener {
    public static final String SIGN_UP_CLICKED = "signUpClicked";

    private final LoginViewModel loginViewModel;
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private LoginController loginController = null;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        loginViewModel.addPropertyChangeListener(this);
        getStyleClass().add("auth-background");

        Label title = new Label("Welcome to\nStudy Nook!");
        title.getStyleClass().add("auth-title");
        title.setTextAlignment(TextAlignment.CENTER);

        usernameField.setPromptText("Enter your username");
        passwordField.setPromptText("Enter your password");
        usernameField.getStyleClass().add("auth-field");
        passwordField.getStyleClass().add("auth-field");

        Button signIn = new Button("Sign in");
        signIn.getStyleClass().add("auth-button");
        signIn.setMaxWidth(Double.MAX_VALUE);
        signIn.setOnAction(e ->
                loginController.execute(usernameField.getText(), passwordField.getText()));

        Label prompt = new Label("Don't have an account?");
        prompt.getStyleClass().add("auth-label");
        Hyperlink signUp = new Hyperlink("Sign up");
        signUp.getStyleClass().add("auth-link");
        signUp.setOnAction(e -> support.firePropertyChange(SIGN_UP_CLICKED, null, true));
        HBox signUpRow = new HBox(4, prompt, signUp);
        signUpRow.setAlignment(Pos.CENTER);

        VBox card = new VBox(8,
                title,
                labelled("Username"), usernameField,
                labelled("Password"), passwordField,
                signIn, signUpRow);
        card.getStyleClass().add("auth-card");
        card.setMaxSize(400, 490);

        getChildren().add(card);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            Platform.runLater(() -> {
                LoginState state = loginViewModel.getState();
                passwordField.clear();

                if (state.getError() != null && !state.getError().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText(state.getError());
                    alert.show();
                }
            });
        }
    }

    public void addNavigationListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Label labelled(String text) {
        final Label label = new Label(text);
        label.getStyleClass().add("auth-label");
        return label;
    }
}