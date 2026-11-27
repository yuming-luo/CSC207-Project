package app;

import interface_adapter.ViewManagerModel;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import view.ViewManager;

import java.util.List;

public class AppFrame extends JFXPanel {
    public AppFrame() {
        Font.loadFont(getClass().getResourceAsStream("fonts/KronaOne-Regular.ttf"), 12);

        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");

        ContentPane content = new ContentPane();
        content.getStyleClass().add("content");
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(content, viewManagerModel);

        Button focusButton = new Button("Focus Session");
        focusButton.setId("focus session");
        Button studyHistoryButton = new Button("Study History");
        studyHistoryButton.setId("study history");
        Button studyGoalButton = new Button("Study Goal");
        studyGoalButton.setId("study goal");

        List<Button> navButtons = List.of(focusButton, studyHistoryButton, studyGoalButton);

        for (Button button: navButtons) {
            button.getStyleClass().add("sidebar-button");
            EventHandler<ActionEvent> event = e -> {
                System.out.println(button.getId());
                viewManagerModel.setState(button.getId());
                viewManagerModel.notifyViewChanged();
                select(button, navButtons);
            };
            button.setOnAction(event);
        }

        select(focusButton, navButtons);

        Button logOutButton = new Button("Log Out");
        logOutButton.getStyleClass().add("sidebar-button");

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebar.getChildren().addAll(focusButton, studyGoalButton, studyHistoryButton, spacer, logOutButton);

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(content);

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        setScene(scene);
    }

    private void select(Button selected, List<Button> all) {
        all.forEach(b -> b.getStyleClass().remove("selected"));
        selected.getStyleClass().add("selected");
    }
}
