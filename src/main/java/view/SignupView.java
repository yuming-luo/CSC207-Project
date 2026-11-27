package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class SignupView extends StackPane implements PropertyChangeListener {

    public static final String LOGIN_CLICKED = "loginClicked";

    private final SignupViewModel signupViewModel;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final PasswordField repeatField = new PasswordField();

    private SignupController signupController = null;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);
        getStyleClass().add("auth-background");

        Label title = new Label("Create your\naccount");
        title.getStyleClass().add("auth-title");
        title.setTextAlignment(TextAlignment.CENTER);

        usernameField.setPromptText("Choose a username");
        passwordField.setPromptText("Choose a password");
        repeatField.setPromptText("Repeat your password");
        usernameField.getStyleClass().add("auth-field");
        passwordField.getStyleClass().add("auth-field");
        repeatField.getStyleClass().add("auth-field");

        Button signUpButton = new Button("Sign up");
        signUpButton.getStyleClass().add("auth-button");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setOnAction(e -> signupController.execute(
                usernameField.getText(), passwordField.getText(), repeatField.getText()));

        Label prompt = new Label("Already have an account?");
        prompt.getStyleClass().add("auth-label");
        Hyperlink logIn = new Hyperlink("Log in");
        logIn.getStyleClass().add("auth-link");
        logIn.setOnAction(e -> support.firePropertyChange(LOGIN_CLICKED, null, true));
        HBox loginRow = new HBox(4, prompt, logIn);
        loginRow.setAlignment(Pos.CENTER);

        VBox card = new VBox(8,
                title,
                labelled("Username"), usernameField,
                labelled("Password"), passwordField,
                labelled("Confirm password"), repeatField,
                signUpButton, loginRow);
        card.getStyleClass().add("auth-card");
        card.setMaxSize(400, 560);

        getChildren().add(card);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            Platform.runLater(() -> {
                SignupState state = signupViewModel.getState();
                passwordField.clear();
                repeatField.clear();

                if (state.getError() != null && !state.getError().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Sign Up Failed");
                    alert.setHeaderText(null);
                    alert.setContentText(state.getError());
                    alert.show();
                }
            });
        }
    }

    public void setSignupController(SignupController signupController) {
        this.signupController = signupController;
    }

    public void addNavigationListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private Label labelled(String text) {
        final Label label = new Label(text);
        label.getStyleClass().add("auth-label");
        return label;
    }
}