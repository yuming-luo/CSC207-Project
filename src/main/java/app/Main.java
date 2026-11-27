package app;


import data_access.InMemoryStudyGoalDataAccessObject;
import data_access.InMemoryStudySessionDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import use_case.login.LoginUserDataAccessInterface;
import use_case.set_study_goal.SetStudyGoalDataAccessInterface;
import use_case.view_study_history.StudySessionDataAccessInterface;
import view.ViewManager;

public class Main extends Application {

    private final LoginUserDataAccessInterface userDao = new InMemoryUserDataAccessObject();
    private StackPane root;

    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 12);

        root = new StackPane();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Study Nook");
        stage.setScene(scene);
        showAuth();
        stage.show();
    }

    private void showAuth() {
        AuthBuilder auth = new AuthBuilder(userDao);
        root.getChildren().setAll(auth.build());

        auth.getLoginViewModel().addPropertyChangeListener(event -> {
            if ("state".equals(event.getPropertyName())
                    && auth.getLoginViewModel().getState().isLoggedIn()) {
                Platform.runLater(this::showApp);
            }
        });
    }

    private void showApp() {
        ContentPane content = new ContentPane();
        ViewManagerModel model = new ViewManagerModel();
        new ViewManager(content, model);

        SetStudyGoalDataAccessInterface goalDao = new InMemoryStudyGoalDataAccessObject();
        StudySessionDataAccessInterface sessionDao = new InMemoryStudySessionDataAccessObject();

        ContentBuilder builder = new ContentBuilder(content, goalDao, sessionDao)
                .addStudyGoalView()
                .addStudyHistoryView();

        model.setState(builder.getNavItems().get(0).viewName());
        model.notifyViewChanged();

        AppFrame frame = new AppFrame(content, model, builder.getNavItems());
        frame.addLogoutListener(e -> {
            if (AppFrame.LOGOUT_CLICKED.equals(e.getPropertyName())) showAuth();
        });
        root.getChildren().setAll(frame);

    }

    public static void main(String[] args) {
        launch(args);
    }
}